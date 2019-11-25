'use strict'
angular.module('tccApp').controller('AgendaController',
		[ "$scope", '$state', 'agendaService', '$rootScope', function($scope, $state, agendaService, $rootScope) {

			if ($state.params.data){
				agendaService.getAgendaDia($state.params.data,function (agendaDia) {
					$scope.agendaDia = agendaDia;
				},function () {
					alert("Não foi possivel recuperar a  agenda para este dia.")
				})
			}else {
				agendaService.getAgendaDia(new Date(),function (agendaDia) {
					$scope.agendaDia = agendaDia;
				},function () {
					alert("Não foi possivel recuperar a  agenda para este dia.")
				})
			}

			var synth = window.speechSynthesis;
			synth.cancel();
			var utterance = new SpeechSynthesisUtterance('Página de agenda.');
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

			$scope.acessarAgendaProxima = function() {
				var proximaData = new Date($state.params.data);
				proximaData.setDate(proximaData.getDate() + 1);
				$state.go('agenda', {
					data : proximaData
				}, {
					reload : true
				});
			};

			$scope.acessarAgendaAnterior = function() {
				var proximaData = new Date($state.params.data);
				proximaData.setDate(proximaData.getDate() - 1);
				$state.go('agenda', {
					data : proximaData
				}, {
					reload : true
				});
			};
	          
		}]);
