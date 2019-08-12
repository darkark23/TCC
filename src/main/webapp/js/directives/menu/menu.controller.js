'use strict'
angular.module('tccApp').controller('MenuController',
		[ "$scope", '$state', 'menuService', '$rootScope', function($scope, $state, menuService, $rootScope) {
			
			var synth = window.speechSynthesis;
			synth.cancel();
			var utterance = new SpeechSynthesisUtterance('Página de Menu de Funcionalidades.');
			utterance.lang = 'pt-BR';
			utterance.rate = 2;
			synth.speak(utterance);
			
			document.onkeyup = function(e) {
				if (e.which == 32) {
					synth.speak(utterance);
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
	            	  } else if(son == 'menu'){
	            		  	synth.cancel();
	            			$state.go('menu', {
							}, {
								reload : true
							});
	            	  } else if(son == 'audio'){
	            		  	synth.cancel();
	            			$state.go('audio-busca', {
							}, {
								reload : true
							});
	            	  } else if(son == 'agenda'){
	            		  	synth.cancel();
	            			$state.go('agenda', {
							}, {
								reload : true
							});
	            	  } else if(son == 'sair'){
	            		  	synth.cancel();
	            			$state.go('principal', {
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
