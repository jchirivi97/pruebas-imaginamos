var servicio = (function() {

	var litsE = [];
	var total = 0;
	var servicios = function() {
		document.getElementById("tick").innerHTML = "TICKET: "
				+ sessionStorage.getItem("ticket");
		document.getElementById("informacion").style.display = "none";
		document.getElementById("continuar").style.display = "none";
		apiServicio.allServicios(verServicios);

	}

	var verServicios = function(datos) {
		for (var i = 0; i < datos.length; i++) {
			var serv = '<div class="card">' + '<div class="card-body">'
					+ '<h5 class="card-title">'
					+ datos[i].idservicio
					+ '</h5>'
					+ '<p class="card-text">'
					+ datos[i].nombre
					+ '</p>'
					+ '<p class="card-text">'
					+ "$ "
					+ datos[i].valor
					+ '</p>'
					+ verCalificacion(datos[i].calificacion)
					+ '</div>'
					+ '<div class="card-footer">'
					+ '<small class="text-muted">'
					+ '<button type="button" class="btn btn-primary" onclick="servicio.agregar(\''
					+ datos[i].idservicio
					+ '\''
					+ ','
					+ '\''
					+ datos[i].nombre
					+ '\''
					+ ','
					+ '\''
					+ datos[i].valor
					+ '\''
					+ ','
					+ '\''
					+ datos[i].calificacion
					+ '\')">Ver Detalles</button>'
					+ '</small>' + '</div>' + '</div>';
			$("#servicios").append(serv);

		}

	}

	var verCalificacion = function(dato) {
		if (dato == 1) {
			return '<p class="card-text">★</p>';
		} else if (dato == 2) {
			return '<p class="card-text">★★</p>';
		} else if (dato == 3) {
			return '<p class="card-text">★★★</p>';
		} else if (dato == 4) {
			return '<p class="card-text">★★★★</p>';
		} else {
			return '<p class="card-text">★★★★★</p>';
		}
	}

	var agregarSevicio = function(id, nombre, valor, calificacion) {
		var servicio = {
			"idservicio" : id,
			"nombre" : nombre,
			"valor" : valor,
			"calificacion" : calificacion
		}

		litsE.push(servicio);
		var lista = '<li class="list-group-item disabled">' + nombre + " "
				+ "$" + valor + '</li>'
		$("#servEscogidos").append(lista)
		total = parseInt(valor, 10) + parseInt(total, 10);

		document.getElementById("total").innerHTML = "Total a Pagar $ " + total;
		if (total > 0) {
			document.getElementById("informacion").style.display = "block";
			document.getElementById("continuar").style.display = "block";
		}
	}

	var guardar = function() {
		var f = new Date();
		var solicitud = {
			"ticket" : sessionStorage.getItem("ticket"),
			"fecha" : f,
			"estado" : "inicial",
			"cedula" : sessionStorage.getItem("user"),
			"tipocc" : sessionStorage.getItem("tipocc")
		};
		apiServicio.saveSolcitud(solicitud);

	}

	var guardarServicios = function() {
		for (var i = 0; i < litsE.length; i++) {
			var solicitudservicio = {
				"ticket" : sessionStorage.getItem("ticket"),
				"idservicio" : parseInt(litsE[i].idservicio, 10)
			};
			apiServicio.saveSolcitudServicio(solicitudservicio);
		}
		location.href = "/creacionSolicitud.html";
	}

	return {
		verAllserv : servicios,
		agregar : agregarSevicio,
		saveSolicitud : guardar,
		save : guardarServicios
	}

})();

var apiServicio = (function() {
	return {
		solicitud : function(ticket,callback) {
			jQuery.ajax({
				url : "/solicitud/"+ticket,
				type : "GET",
				success : function(datos) {
					callback(datos);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("No existe solcitud");
				}
			})
		},
		allServicios : function(callback) {
			jQuery.ajax({
				url : "/servicio/all",
				type : "GET",
				success : function(datos) {
					callback(datos);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("No hay servicios");
				}
			})
		},
		saveSolcitud : function(solicitud) {
			jQuery.ajax({
				url : "/solicitud/",
				type : "POST",
				data : JSON.stringify(solicitud),
				contentType : "application/json",
				success : function() {
					alert("solicitud registrada");
					servicio.save();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("intente nuevamente");
				}
			});
		},
		saveSolcitudServicio : function(solicitudservicio) {
			jQuery.ajax({
				url : "/solicitudServ/",
				type : "POST",
				data : JSON.stringify(solicitudservicio),
				contentType : "application/json",
				success : function() {
					alert("agrego el servicio registrada");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("intente nuevamente");
				}
			});
		}
	}
})();