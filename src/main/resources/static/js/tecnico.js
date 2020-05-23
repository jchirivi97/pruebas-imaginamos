var tecnico = (function() {

	var cantTokens = 0;
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

	return {
		generar : tokens
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
		}

	}
})();
