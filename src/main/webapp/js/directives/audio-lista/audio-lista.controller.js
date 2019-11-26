'use strict'
angular.module('tccApp').controller('AudioListaController',
		[ "$scope", '$state', 'audioListaService', '$rootScope', function($scope, $state, audioListaService, $rootScope) {

	comecarReconhecimento();
	$scope.audioLivros = null;
	var currentResultado = 0;

	audioListaService.getListaAudioLivroTermo($state.params.termo,function (listaAudioLivro) {
		$scope.audioLivros = listaAudioLivro;
		synth.cancel();
		if(!$scope.audioLivros){
			reproduzirFrase(getAudio.audioLista.intro + ' ' + getAudio.audioLista.naoEncontrado);
		}else {
			if(listaAudioLivro.length == 1){
				reproduzirFrase(getAudio.audioLista.intro + ' Foi encontrado um resultado.' );
			}else {
				reproduzirFrase(getAudio.audioLista.intro + ' Foram encontrados ' + listaAudioLivro.length + 'resultados.' );
			}
		};
	},function () {
		alert("Não foi possivel recuperar a lista de áudio livros.")
	});

	$scope.acessarAudio = function(id) {
		$state.go('audio',{id : id}, {reload : true});
	};

	$scope.voltar = function() {
		$state.go('audioBusca',{}, {reload : true});
	};

	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript.trim();
				if (son == 'menu') {
					synth.cancel();
					$state.go('menu', {}, {reload: true});
				} else if (son == 'agenda') {
					synth.cancel();
					$state.go('agenda',{data : new Date()}, {reload : true});
				} else if (son == 'voltar') {
					synth.cancel();
					$scope.voltar();
				} else if (son == 'resultados') {
					reproduzirFrase('Resultados.');
					currentResultado = 1;
					reproduzirLista();
				} else if (son == 'repetir' && currentResultado != 0) {
					reproduzirLista();
				} else if (son == 'próximo' && currentResultado != 0) {
					++currentResultado;
					reproduzirLista();
				}else if (son == 'escutar' && currentResultado != 0) {
					$scope.acessarAudio($scope.audioLivros[currentResultado - 1].id);
				} else if (son == 'anterior' && currentResultado != 0) {
					if (currentResultado > 1) {
						--currentResultado;
						reproduzirLista();
					} else {
						reproduzirFrase('Não existem resultados anteriores ao atual.');
					}
				}else {
					reproduzirFrase('Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda.');
				}
			}
		}
	};

	function reproduzirLista () {
		if (!$scope.audioLivros){
			reproduzirFrase(getAudio.principal.semResultado);
			currentResultado = 0;
		}else if(currentResultado > $scope.audioLivros.length){
			reproduzirFrase(getAudio.principal.ultimoResultado);
			--currentResultado;
		}else if ($scope.audioLivros){
			document.getElementById("botao"+currentResultado).focus();
			reproduzirFrase(" Resultado " + currentResultado + " : " + $scope.audioLivros[currentResultado - 1].titulo + ". Diga ouvir para acessar o áudio livro, próximo ou anterior para ouvir outros resultados. Para escutar novamente diga repetir.");
		} else {
			reproduzirFrase(getAudio.audioLista.semResultado);
			currentResultado = 0;
		}
	};

}]);
