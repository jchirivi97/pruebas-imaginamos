var usuario = (function(){
	
	var cantSolicitudes = 0;
	var servicios = [];
	
	var login = function(){
		var user = document.getElementById("cedula").value;
		var password = document.getElementById("password").value;
		var tipocc = document.getElementById("tipocc").value;
		apiUser.loginUser(user,tipocc,password);
		sessionStorage.setItem("user",user);
		sessionStorage.setItem("tipocc",tipocc);
	}
	
	
	var loginTecnico = function(){
		var user = document.getElementById("cedula").value;
		var password = document.getElementById("password").value;
		var tipocc = document.getElementById("tipocc").value;
		apiUser.loginTecnico(user,tipocc,password);
		sessionStorage.setItem("user",user);
		sessionStorage.setItem("tipocc",tipocc);
	}
	
	var infoUsuario = function(){
		var user = sessionStorage.getItem("user");
		var tipocc = sessionStorage.getItem("tipocc");
		apiUser.infoUser(user,tipocc,cargar);
	}
	
	var infoUser = function (cedula,tipocc){
		apiUser.infoUser(cedula,tipocc,cargar);
	}
	
	var cargar = function(datos){
		document.getElementById("usuario").innerHTML ="Cliente: "+ datos.nombre +" "+ datos.apellido
	}
	
	var solicitudes = function(){
		apiUser.allSolicitudes(versolicitudes);
	}
	
	var versolicitudes = function(datos){
		cantSolicitudes = datos.length + 1;
		sessionStorage.setItem("ticket",cantSolicitudes);
		location.href="/allServices.html";
	}
	
	var solicitudesTecnico = function(){
		var user = sessionStorage.getItem("user");
		var tipocc = sessionStorage.getItem("tipocc");
		apiUser.allTecnicoSolicitudes(user,tipocc,versolicitudesTecnico);
	}
	
	var versolicitudesTecnico = function (datos){
		for(var i = 0; i < datos.length; i++){
			var column = '<tr><th scope="row">' + datos[i].token + '</th>'
			+'<td>'+ datos[i].ticket+'</td>'
		      +'<td>'+ '<button type="button" class="btn btn-primary" onclick="usuario.detalle(\''
				+ datos[i].ticket + '\')">Detalles</button>' +'</td></tr>';
			$("table tbody").append(column);
		}
	}
	
	var verDetalle = function(dato){
		sessionStorage.setItem("ticket",dato);
		location.href = "/detalle.html"
	}
	
	var infoticket = function(){
		apiServicio.solicitud(sessionStorage.getItem("ticket"),verticket);
		apitecnico.servicioSolicitud(sessionStorage.getItem("ticket"),verservicios);
		
	}
	
	var verticket = function(datos){
		document.getElementById("ticket").innerHTML = "TICKET : " + datos.ticket;
		document.getElementById("fecha").innerHTML = "Fecha : " + datos.fecha;
		document.getElementById("estado").innerHTML = "Estado : " + datos.estado;
		infoUser(datos.cedula,datos.tipocc);
		
	}
	
	var verservicios = function(datos) {
		for(var i = 0; i < datos.length ; i++){
			var linea = '<li class="list-group-item">'+ datos[i].idservicio +" - "+ datos[i].nombre +'</li>'
			$("#servicios").append(linea);
		}
	}
	
	
	
	return {
		ingreso : login,
		ingresoT : loginTecnico,
		informacion : infoUsuario,
		ticketSolicitud : solicitudes,
		detalle : verDetalle,
		cargar : solicitudesTecnico,
		inforTick : infoticket
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
		loginTecnico: function(cedula,tipocc,password){
			jQuery.ajax({
				url: "/tecnico/"+ cedula +"/"+ tipocc +"/"+password,
				type: "GET",
				success: function(){
					alert("Ingreso exito.");
					location.href = "/paginaTecnico.html";
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
		},
		allTecnicoSolicitudes: function(cedula,tipocc,callback){
			jQuery.ajax({
				url: "/tenicoSol/all/"+cedula+"/"+tipocc,
				type: "GET",
				success: function(datos){
					callback(datos);
				},error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                alert("No hay solcitudes"); 
	            }
			})
		},
		logout : function(){
			sessionStorage.setItem("user", "" );
			sessionStorage.setItem("tipocc","");
			location.href="/loginUser.html"
		},
		volver: function(){
			location.href="/paginaTecnico.html"
		}
		
	}
})();