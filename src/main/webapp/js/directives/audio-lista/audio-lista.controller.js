'use strict'
angular.module('tccApp').controller('AudioListaController',
		[ "$scope", '$state', 'audioListaService', '$rootScope', function($scope, $state, audioListaService, $rootScope) {

			var frasePrincipal = 'Página de resultado de Consulta de áudio livro. ';
			var naoEncontrado = 'Não foram encontrados áudio livros com o termo informado, diga voltar para retornar a tela de busca e tentar outro termo.';

			audioListaService.getListaAudioLivroTermo($state.params.termo,function (listaAudioLivro) {
				$scope.audioLivros = listaAudioLivro;
			},function () {
				alert("Não foi possivel recuperar a lista de áudio livros.")
			})

			synth.cancel();
			if(!$scope.audioLivros){
				reproduzirFrase(frasePrincipal + naoEncontrado);
			}else {
				reproduzirFrase(frasePrincipal);
			};
			comecarReconhecimento();

			recognition.onresult = function(event) {
				for (let i = event.resultIndex; i < event.results.length; i++) {
					if (event.results[i].isFinal) {
						var son = event.results[i][0].transcript
							.trim();
						console.log(son);
						if (son == 'voltar') {
							synth.cancel();
							$scope.voltar();
						} else if (son == 'parar') {
							synth.cancel();
							$scope.pararAudio();
						} else {
							reproduzirFrase('Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda.');
						}
					}
				}
			};

			$scope.acessarAudio = function(id) {
				$state.go('audio', {
					id : id
				}, {
					reload : true
				});
			};

			$scope.voltar = function() {
				$state.go('audioBusca', {
				}, {
					reload : true
				});
			};

		}]);
