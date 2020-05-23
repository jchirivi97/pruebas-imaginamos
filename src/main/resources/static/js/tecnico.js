var tecnico = (function(){
	
	var cantTokens = 0;	
	var tecnicos = function(){
		apitecnico.allTecnicos(vertecnicos);
	}
	
	var tokens = function(){
		apitecnico.allTokens(generarToken);
		tecnicos();
	}
	
	var vertecnicos = function(datos){
		var numero = Math.random() * (datos.length - 0) + 0;
		document.getElementById("nombre").innerHTML = datos[numero].nombre +" "+datos[numero].apellido
		document.getElementById("telTec").innerHTML = datos[numero].celular
		
		var tecnicosolicitud = {"tocken":cantTokens,"ticket":sessionStorage.getItem("ticket"),"tecnico":datos[numero].cedula,"tipocc":datos[numero].tipocc};
		apitecnico.saveTecnicoSolicitud(tecnicosolicitud);
	}
	
	var generarToken = function(datos){
		cantTokens = datos.length + 1;
		document.getElementById("token").innerHTML = cantTokens;
	}
	
		
	return{
		generarToken =  tokens
	}
	
	
})();


var apitecnico = (function(){
	return{
		
		allTecnicos : function(callback){
			jQuery.ajax({
				url: "/tecnico/all",
				type: "GET",
				success: function(datos){
					callback(datos);
				},error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                alert("no hay tenicos"); 
	            }
			})
		},
		allTokens : function(callback){
			jQuery.ajax({
				url: "/tenicoSol/all",
				type: "GET",
				success: function(datos){
					callback(datos);
				},error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                alert("no hay tokens"); 
	            }
			})
		},
		saveTecnicoSolicitud : function(tecnicosolicitud){
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

