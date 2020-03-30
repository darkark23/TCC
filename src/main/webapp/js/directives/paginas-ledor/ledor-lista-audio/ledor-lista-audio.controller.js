'use strict'
angular.module('tccApp').controller('LedorListaAudioController',
		[ "$scope", '$state', 'ledorListaAudioService', 'loginService', '$rootScope', function($scope, $state, ledorListaAudioService, loginService,  $rootScope) {

	loginService.confirmarUsuario($scope.dados,
		function (usuarioPerfil) {
			if (usuarioPerfil.existente == true){
				synth.cancel();
				$rootScope.usuarioPerfil = usuarioPerfil;
				carregarListaLivros($rootScope.usuarioPerfil.login);
			}else{}
		},function () {
		});

	comecarReconhecimento();
	$scope.audioLivros = null;
	var currentResultado = 0;

	$scope.acessarAudio = function(id) {
		$state.go('audio',{id : id}, {reload : true});
	};

	$scope.voltar = function() {
		$state.go('menu',{}, {reload : true});
	};

	var reproduzirIntro = function() {
		if(!$scope.audioLivros || $scope.audioLivros.length == 0 ){
			reproduzirFrase(getAudio.audioLista.intro + ' ' + getAudio.audioLista.naoEncontrado);
		}else {
			if($scope.audioLivros.length == 1){
				reproduzirFrase(getAudio.audioLista.intro + ' Foi encontrado um resultado.' );
			}else {
				reproduzirFrase(getAudio.audioLista.intro + ' Foram encontrados ' + $scope.audioLivros.length + ' resultados. ' + getAudio.audioLista.comandos );
			}
		}
	};

	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript.trim();
				if (son == 'resultados') {
					currentResultado = 1;
					reproduzirLista();
				} else if (son == 'repetir' && currentResultado != 0) {
					reproduzirLista();
				} else if (son == 'próximo' && currentResultado != 0) {
					if (currentResultado < $scope.audioLivros.length ) {
						++currentResultado;
						reproduzirLista();
					} else {
						reproduzirFrase(getAudio.audioLista.ultimoResultado);
					}
				} else if (son == 'anterior' && currentResultado != 0) {
					if (currentResultado > 1) {
						--currentResultado;
						reproduzirLista();
					} else {
						reproduzirFrase(getAudio.audioLista.primeiroResultado);
					}
				} else if (son == 'escutar' && currentResultado != 0) {
					$scope.acessarAudio($scope.audioLivros[currentResultado - 1].id);
				} else if(son == 'menu') {
					synth.cancel();
					$state.go('menu',{},{reload : true});
				} else if (son == 'áudio' || son == 'voltar') {
					synth.cancel();
					$state.go('audioBusca',{},{reload : true});
				} else if (son == 'agenda') {
					synth.cancel();
					$state.go('agenda',{data : new Date()}, {reload : true});
				} else if (son == 'sair') {
					synth.cancel();
					$state.go('principal', {}, {reload : true});
				} else if (son == 'ajuda') {
					reproduzirIntro()
				} else if (son == 'outros') {
					reproduzirFrase(getAudio.audioLista.fraseAjuda);
				} else {
					reproduzirFrase(getAudio.ajuda);
				}
			}
		}
	};

	document.onkeyup = function(e) {
		if (e.which == 96) {
			reproduzirFrase(getAudio.menu.intro);
		} else if (e.which == 49) {
			synth.cancel();
			$state.go('audio-busca',{}, {reload : true});
		} else if (e.which == 50) {
			synth.cancel();
			$state.go('agenda',{data : new Date()}, {reload : true});
		} else if (e.which == 51) {
			synth.cancel();
			$state.go('principal',{}, {reload : true});
		} else if (e.which == 52) {
			reproduzirFrase(getAudio.menu.fraseAjuda);
		}
	};

	function reproduzirLista () {
		if (!$scope.audioLivros){
			reproduzirFrase(getAudio.principal.semResultado);
			currentResultado = 0;
		}else if ($scope.audioLivros){
			document.getElementById("botao"+currentResultado).focus();
			reproduzirFrase(" Resultado " + currentResultado + " : " + $scope.audioLivros[currentResultado - 1].titulo + ". " + getAudio.audioLista.comandos2 );
		} else {
			reproduzirFrase(getAudio.audioLista.semResultado);
			currentResultado = 0;
		}
	};

	function carregarListaLivros(login) {
		let dados = {usuario : null};
		dados.usuario = login;
		ledorListaAudioService.findAllLedor(dados,
			function (lista) {
				$scope.audioLivros = lista;
			},function () {
			})
	}

	$scope.remover = function(id) {
		ledorListaAudioService.remover(id,function (status) {
			if(status == 1){
				console.log('Removido com sucesso!')
				$state.go('audioListaLedor', {}, {reload : true});
			}else {
				console.log('Erro ao remover o audio livro!')
				$state.go('audioListaLedor', {}, {reload : true});
			}
		},function () {
			console.log('Erro ao remover o audio livro!')
			$state.go('audioListaLedor', {}, {reload : true});
		})
	}

	$scope.adicionar = function() {
		$state.go('audioEditarLedor', {}, {reload : true});
	}

	$scope.editar = function(id) {
		$state.go('audioEditarLedor',{id : id}, {reload : true});
	};

	$scope.logOff = function() {
		loginService.logOffUsuario();
	};
}]);
