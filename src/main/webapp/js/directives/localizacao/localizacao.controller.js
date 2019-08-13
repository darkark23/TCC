'use strict'
angular.module('tccApp').controller('LocalizacaoController',
		[ "$scope", '$state', 'localizacaoService', '$rootScope', function($scope, $state, localizacaoService, $rootScope) {
			
			var synth = window.speechSynthesis;
			synth.cancel();
			var utterance = new SpeechSynthesisUtterance('Página de login. Diga usuário para começar a informar um usuário. Diga senha para começar a informar um usuario. Diga logar depois de informar o usuário e a senha para tentar logar no sistema. Diga principal para acessar a página principal.');
			utterance.lang = 'pt-BR';
			utterance.rate = 2;
			synth.speak(utterance);
			
			document.onkeyup = function(e) {
				if (e.which == 32) {
					synth.speak(utterance);
				}  else if (e.which == 49) {
        		  	synth.cancel();
        			$state.go('login', {
					}, {
						reload : true
					});
				}  else if (e.which == 40) {
        		  	synth.cancel();
        			$state.go('menu', {
					}, {
						reload : true
					});
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
	            	  if (son == 'principal'){
	            		  synth.cancel();
	            		  $state.go('principal', {
							}, {
								reload : true
							});
	            	  } else if(son == 'login'){
	            		  	synth.cancel();
	            			$state.go('login', {
							}, {
								reload : true
							});
	            	  } else if(son == 'entrar'){
	            		  	synth.cancel();
	            			$state.go('menu', {
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
