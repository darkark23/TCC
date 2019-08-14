'use strict'
angular.module('tccApp').controller('LocalizacaoController',
		[ "$scope", '$state', 'localizacaoService', '$rootScope', function($scope, $state, localizacaoService, $rootScope) {

			var synth = window.speechSynthesis;
			var utterance = null;
			var recognition = null;
			var frasePrincipal = 'Página de Localização. O Centro de Ensino Especial de Deficientes Visuais está localizado na SGAS II Quadra 612 Sul - Asa Sul, Brasília – DF. CEP: 70200-000.'; 
			var fraseAjuda = 'Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.' ;
			
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
					$state.go('principal', {}, {
						reload : true
					});
				} else if (e.which == 98) {
					synth.cancel();
					$state.go('contato', {}, {
						reload : true
					});
				} else if (e.which == 99) {
					synth.cancel();
					$state.go('localizacao', {}, {
						reload : true
					});
				} else if (e.which == 100) {
					synth.cancel();
					$state.go('login', {}, {
						reload : true
					});
				} else if (e.which == 105) {
					reproduzirFrase();
				}
			};

			comecarReconhecimento();
			
			recognition.onresult = function(event) {
				for (let i = event.resultIndex; i < event.results.length; i++) {
					if (event.results[i].isFinal) {
						var son = event.results[i][0].transcript
								.trim();
						console.log(son);
						if (son == 'principal') {
							synth.cancel();
							$state.go('principal', {}, {
								reload : true
							});
						} else if (son == 'contato') {
							synth.cancel();
							$state.go('contato', {}, {
								reload : true
							});
						} else if (son == 'localização') {
							synth.cancel();
							$state.go('localizacao', {}, {
								reload : true
							});
						} else if (son == 'login') {
							synth.cancel();
							$state.go('login', {}, {
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
