'use strict'
angular.module('tccApp').controller('LoginController',
		[ "$scope", '$state', 'loginService', '$rootScope', function($scope, $state, loginService, $rootScope) {
			
			$scope.dados = {
					usuario : null,
					senha : null
			};
			var synth = window.speechSynthesis;
			var tipoInput = 0;
			var utterance = null;
			var recognition = null;
			var frasePrincipal = 'Página login. Diga usuário para informar um usuário via teclado, senha para informar uma senha. Aperte seta para baixo para submeter o usuário e senha. Caso a senha ou o usuário estejam incorretos diga o nome do campo que queira corrigir e digite outra vez.';
			var fraseAjuda = 'Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login. Fonte exame.abril.com.br/estilo-de-vida/designer-cria-fonte-que-une-braile-ao-alfabeto-tradicional/' ;
			
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
				}else if (e.which == 40) {
					synth.cancel();
					$state.go('menu', {}, {
						reload : true
					});
				}  else if (e.which == 105) {
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
						console.log(tipoInput);
						console.log($scope.dados.usuario);
						console.log($scope.dados.senha);
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
						} else if (son == 'entrar') {
							if(!$scope.dados.usuario){
								reproduzirFrase('Por favor informe um usuário.');
								tipoInput = 1;
							} else if(!$scope.dados.senha){
								reproduzirFrase('Por favor Informe uma senha.');
								tipoInput = 2;
							} else if(!$scope.dados.usuario && !$scope.dados.senha){
								reproduzirFrase('Por favor Informe uma senha e um usuário.');
								tipoInput = 0;
							} else if($scope.dados.usuario != 'teste' || $scope.dados.senha != 'teste'){
								reproduzirFrase('Senha ou usuário incorreto.');
								tipoInput = 0;
							} else {
								synth.cancel();
								$state.go('menu', {}, {
									reload : true
								});
							}
						}else if (son == 'usuário') {
							if($scope.dados.usuario){
								document.getElementById("usuario").focus();
								tipoInput = 1;
								reproduzirFrase('O usuário digitado foi ' + $scope.dados.usuario + '. Informe um novo usuário.');
							} else {
								document.getElementById("usuario").focus();
								tipoInput = 1;
								reproduzirFrase('Informe um usuário.');
							}
						} else if (son == 'senha') {
							if($scope.dados.senha){
								document.getElementById("senha").focus();
								tipoInput = 2;
								reproduzirFrase('A senha informada foi ' + $scope.dados.senha + '. Informe uma nova senha.');
							} else {
								document.getElementById("senha").focus();
								tipoInput = 2;
								reproduzirFrase('Informe uma senha.');
							}
						} else if (son == 'outros' || son == 'ajuda') {
							reproduzirFrase(fraseAjuda);
						} else if (son == 'repetir') {
							reproduzirFrase(frasePrincipal);
						} else if(tipoInput == 0) {
							reproduzirFrase('Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda.');
						} else if (tipoInput == 1){
							$scope.dados.usuario = son;
							document.getElementById("usuario").value = son;
							document.getElementById("usuario").blur();
							tipoInput = 0;
							reproduzirFrase('O usuário digitado foi ' + son);
						} else if (tipoInput == 2){
							document.getElementById("senha").blur();
							document.getElementById("senha").value = son;
							$scope.dados.senha = son;
							tipoInput = 0;
							reproduzirFrase('A senha digitada foi ' + son);
						}
					}
				}
			};
			
		}]);
