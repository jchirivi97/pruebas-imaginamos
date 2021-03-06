var tecnico = (function() {

	var cantTokens = 0;
	var total = 0;
	var servis = [];
	var tecnicos = function() {
		apitecnico.allTecnicos(vertecnicos);
	}

	var tokens = function() {
		apitecnico.allTokens(generarToken);
		tecnicos();
	}

	var vertecnicos = function(datos) {
		var numero = parseInt(Math.random() * (datos.length - 0) + 0, 10);
		document.getElementById("nombre").innerHTML = datos[numero].nombre
				+ " " + datos[numero].apellido
		document.getElementById("telTec").innerHTML = "Celular: "
				+ datos[numero].celular
		var tecnicosolicitud = {
			"tocken" : cantTokens,
			"ticket" : sessionStorage.getItem("ticket"),
			"tenico" : datos[numero].cedula,
			"tipocc" : datos[numero].tipoCC
		};
		apitecnico.saveTecnicoSolicitud(tecnicosolicitud);
	}

	var generarToken = function(datos) {
		cantTokens = datos.length + 1;
		document.getElementById("token").innerHTML = "TOKEN: " + cantTokens;
	}

	var serviciosSolicitud = function(ticket) {
		apitecnico.servicioSolicitud(ticket,verServicios);
	}

	var verServicios = function(datos) {
		if (datos.length > 0) {
			for (var i = 0; i < datos.length; i++) {
				var linea = '<li class="list-group-item disabled">'
						+ datos[i].nombre + " $ " + datos[i].valor + '</li>'
				$("#servicios").append(linea);
				servis.push(datos[i]);
				total = parseInt(datos[i].valor, 10) + parseInt(total, 10);
			}
			document.getElementById("total").innerHTML = "TOTAL : $" + total;
		}

	}

	var tecnicosSolicitud = function(ticket) {
		apitecnico.TecnicoSolicitud(ticket,verTecnSolicitud);
	}

	var verTecnSolicitud = function(datos) {
		var linea = '<li class="list-group-item disabled">' + datos.nombre
				+ "" + datos.apellido + '</li>'
		$("#tecnico").append(linea);
	}

	var verinfoTicket = function() {
		serviciosSolicitud(sessionStorage.getItem("ticket"));
		tecnicosSolicitud(sessionStorage.getItem("ticket"));
		infoSolicitud(sessionStorage.getItem("ticket"));
		document.getElementById("calificiacion").style.display = "none";
	}
	

	var infoSolicitud = function(ticket) {
		apiServicio.solicitud(ticket,verinfoSolicitud);
	}

	var verinfoSolicitud = function(datos) {
		var linea;
		if (datos.estado == "inicial"){
			linea= '<img class="card-img-top" src="/files/img/recibido.png" alt="Card image cap">'
			+'<div class="card-body">'
			+'<p class="card-text">El estado de tu solicitud esta en: Inicial</p>'
			+'</div>'
		}
		else if (datos.estado == "reparando"){
			linea= '<img class="card-img-top" src="/files/img/reparando.jpg" alt="Card image cap">'
				+'<div class="card-body">'
				+'<p class="card-text">El estado de tu solicitud esta en: Reparando</p>'
				+'</div>'
		}
		else if (datos.estado == "pruebas"){
			linea= '<img class="card-img-top" src="/files/img/testing.jpg" alt="Card image cap">'
				+'<div class="card-body">'
				+'<p class="card-text">El estado de tu solicitud esta en: Pruebas</p>'
				+'</div>'
		}
		else{
			linea= '<img class="card-img-top" src="/files/img/finalizado.png" alt="Card image cap">'
				+'<div class="card-body">'
				+'<p class="card-text">El estado de tu solicitud esta en: Finalizado</p>'
				+'</div>'
				document.getElementById("calificiacion").style.display = "block";
		}
		$("#estado").append(linea);
	}
	
	var calificacion =function(){
		var calificacion = document.getElementById("calif").value;
		var lista = JSON.parse(sessionStorage.getItem("servicios"));
		for(var i = 0; i < lista.length; i++){
			lista[i].calificacion = calificacion;
			apitecnico.updateCalificacion(lista[i]);
		}
		
	}
	
	var irCalif = function(){
		sessionStorage.setItem("servicios",JSON.stringify(servis));
		location.href = "/calificacion.html";
	}

	return {
		generar : tokens,
		ticketInfo : verinfoTicket,
		irCalificacion : irCalif,
		guardarCalifi : calificacion
	}

})();

var apitecnico = (function() {
	return {

		allTecnicos : function(callback) {
			jQuery.ajax({
				url : "/tecnico/all",
				type : "GET",
				success : function(datos) {
					callback(datos);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("no hay tenicos");
				}
			})
		},
		allTokens : function(callback) {
			jQuery.ajax({
				url : "/tenicoSol/all",
				type : "GET",
				success : function(datos) {
					callback(datos);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("no hay tokens");
				}
			})
		},
		saveTecnicoSolicitud : function(tecnicosolicitud) {
			jQuery.ajax({
				url : "/tenicoSol/",
				type : "POST",
				data : JSON.stringify(tecnicosolicitud),
				contentType : "application/json",
				success : function() {
					alert("Tecnico asignado Correctamente");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("intente nuevamente");
				}
			});
		},

		TecnicoSolicitud : function(ticket, callback) {
			jQuery.ajax({
				url : "/tecnico/" + ticket,
				type : "GET",
				success : function(datos) {
					callback(datos);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("no hay tenicos");
				}
			});
		},

		servicioSolicitud : function(ticket, callback) {
			jQuery.ajax({
				url : "/servicio/solicitud/" + ticket,
				type : "GET",
				success : function(datos) {
					callback(datos);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("no existen servicios");
				}
			});
		},
		updateCalificacion : function(servicio) {
			jQuery.ajax({
				url: "/servicio/update/",
				type: "PUT",
				data: JSON.stringify(servicio),
				contentType: "application/json",
				success: function(){
					alert("calificacion almacenada");
					location.href = "/paginaUser.html"
				},error: function(XMLHttpRequest, textStatus, errorThrown) { 
                    alert("Intente nuevamente"); 
                }
			});
		},
		
		irVerEstado : function(){
			location.href = "/seguimientoActual.html";
		},
		
		irVerEstadoAnyWhere : function(){
			sessionStorage.setItem("ticket",document.getElementById("bticket").value);
			location.href = "/seguimientoActual.html";
			
		}		
		
	}
})();
