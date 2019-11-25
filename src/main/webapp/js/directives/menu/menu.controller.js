'use strict'
angular.module('tccApp').controller('MenuController',
		[ "$scope", '$state', 'menuService', '$rootScope', function($scope, $state, menuService, $rootScope) {

			var synth = window.speechSynthesis;
			var utterance = null;
			var recognition = null;
			var frasePrincipal = 'Menu de seleção de funcionalidades. Diga áudio para procura um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual.';
			var fraseAjuda = 'Você tem as seguintes opções, diga áudio para procura um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual. Você pode também apertar os botões de um a três no teclado numérico para as respectivas opções, áudio, agenda e sair' ;

			$scope.acessarAgenda = function() {
				$state.go('agenda', {
					data : new Date()
				}, {
					reload : true
				});
			};
			
			var reproduzirFrase = function(frase) {
				synth.cancel();
				utterance = new SpeechSynthesisUtterance(frase);
				utterance.lang = 'pt-BR';
				utterance.rate = 2;
				synth.speak(utterance);
			}
			
			var comecarReconhecimento = function(){
				recognition = new webkitSpeechRecognition();
				recognition.interimResults = true;
				recognition.lang = "pt-BR";
				recognition.continuous = true;
				recognition.start();
			}
			
			synth.cancel();
			reproduzirFrase(frasePrincipal);

			document.onkeyup = function(e) {
				if (e.which == 96) {
					reproduzirFrase(frasePrincipal);
				} else if (e.which == 97) {
					synth.cancel();
					$state.go('audio-busca', {}, {
						reload : true
					});
				} else if (e.which == 98) {
					synth.cancel();
					$state.go('agenda', {}, {
						reload : true
					});
				} else if (e.which == 99) {
					synth.cancel();
					$state.go('principal', {}, {
						reload : true
					});
				} else if (e.which == 105) {
					reproduzirFrase(fraseAjuda);
				}
			};

			comecarReconhecimento();
			
			recognition.onresult = function(event) {
				for (let i = event.resultIndex; i < event.results.length; i++) {
					if (event.results[i].isFinal) {
						var son = event.results[i][0].transcript
								.trim();
						console.log(son);
						if (son == 'menu') {
							synth.cancel();
							$state.go('menu', {}, {
								reload : true
							});
						} else if (son == 'áudio') {
							synth.cancel();
							$state.go('audio-busca', {}, {
								reload : true
							});
						} else if (son == 'agenda') {
							synth.cancel();
							$state.go('agenda', {}, {
								reload : true
							});
						} else if (son == 'sair') {
							synth.cancel();
							$state.go('principal', {}, {
								reload : true
							});
						} else if (son == 'outros' || son == 'ajuda') {
							reproduzirFrase(fraseAjuda);
						} else if (son == 'repetir') {
							reproduzirFrase(frasePrincipal);
						} else {
							reproduzirFrase('Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda.');
						}
					}
				}
			};
	          
		}]);
