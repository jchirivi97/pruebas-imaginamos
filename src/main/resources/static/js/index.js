var usuario = (function(){
	
	var cantSolicitudes = 0;
	
	var login = function(){
		var user = document.getElementById("cedula").value;
		var password = document.getElementById("password").value;
		var tipocc = document.getElementById("tipocc").value;
		apiUser.loginUser(user,tipocc,password);
		sessionStorage.setItem("user",user);
		sessionStorage.setItem("tipocc",tipocc);
	}
	
	var infoUsuario = function(){
		var user = sessionStorage.getItem("user");
		var tipocc = sessionStorage.getItem("tipocc");
		apiUser.infoUser(user,tipocc,cargar);
	}
	
	var cargar = function(datos){
		document.getElementById("usuario").innerHTML = datos.nombre +" "+ datos.apellido
	}
	
	var solicitudes = function(){
		apiUser.allSolicitudes(versolicitudes);
	}
	
	var versolicitudes = function(datos){
		cantSolicitudes = datos.length + 1;
		sessionStorage.setItem("ticket",cantSolicitudes);
		location.href="/allServices.html";
	}
	
	return {
		ingreso : login,
		informacion : infoUsuario,
		ticketSolicitud : solicitudes
	}
	
})();



var apiUser = (function(){
	return{
		loginUser: function(cedula,tipocc,password){
			jQuery.ajax({
				url: "/cliente/"+ cedula +"/"+ tipocc +"/"+password,
				type: "GET",
				success: function(){
					alert("Ingreso exito.");
					location.href = "/paginaUser.html";
				},error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                alert("El Usuario o Contraseña no es correcto"); 
	            }
			})
		},
		
		infoUser: function(cedula,tipocc,callback){
			jQuery.ajax({
				url: "/cliente/"+ cedula +"/"+ tipocc,
				type: "GET",
				success: function(datos){
					callback(datos);
				},error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                alert("El Usuario o Contraseña no es correcto"); 
	            }
			})
		},		
		allSolicitudes: function(callback){
			jQuery.ajax({
				url: "/solicitud/all",
				type: "GET",
				success: function(datos){
					callback(datos);
				},error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                alert("No hay solcitudes"); 
	            }
			})
		}
		
	}
})();