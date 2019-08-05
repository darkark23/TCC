'use strict'
angular.module('tccApp').controller('HomeController',
		[ "$scope", '$state', 'homeService', '$rootScope', function($scope, $state, homeService, $rootScope) {
			$rootScope.nav = 0;
			$scope.microfone ='dsads';
			
			document.onkeyup = function(e) {
				  if (e.which == 32) {
				    /*alert("M key was pressed");*/
				    var msg = new SpeechSynthesisUtterance('Página principal. Bem vindo ao Portal do Ledor. Diga home para acessar a página principal, diga cadastro para acessar a página de cadastro ou consulta para acessar a página consulta');
				    msg.lang = 'pt-BR';
				    msg.rate = 2;
					window.speechSynthesis.speak(msg);
				  } 
				};
			
			
				
	          const recognition = new webkitSpeechRecognition();
	          recognition.interimResults = true;
	          recognition.lang = "pt-BR";
	          recognition.continuous = true;
	          recognition.start();
	          // This event happens when you talk in the microphone
	          recognition.onresult = function(event) {
	            for (let i = event.resultIndex; i < event.results.length; i++) {
	              if (event.results[i].isFinal) {
	            	  var son = event.results[i][0].transcript.trim();
	            	  if (son == 'home'){
	            		  $state.go('home', {
							}, {
								reload : true
							});
	            	  }else if(son == 'cadastro'){
	            			$state.go('cadastro', {
							}, {
								reload : true
							});
	            	  }else if(son == 'consulta'){
	            		  $state.go('consulta', {
							}, {
								reload : true
							});
	            	  }
	            	 
	              }
	            }
	          };
	          
	          $scope.atualizar = function(string) {
	        	  console.log(string);
	        	  $scope.microfone = string;
				};
	          
	          
		
		}]);
