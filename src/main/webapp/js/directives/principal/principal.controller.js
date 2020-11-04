'use strict'
angular.module('tccApp').controller('PrincipalController',
	["$scope",'$state','principalService', 'loginService','$rootScope',function($scope, $state, principalService, loginService, $rootScope) {

	var currentNoticia = 0;
	$scope.noticias = null;

	principalService.getNoticias(function (listaNoticias) {
		$scope.noticias = listaNoticias;
	},function () {
	});

	$scope.logar = function(){
		let dados = {usuario : null,	senha : null};
		loginService.confirmarUsuario(dados,
			function (usuarioPerfil) {
				if (usuarioPerfil.existente == true){
					synth.cancel();
					$rootScope.usuarioPerfil = usuarioPerfil;
					$state.go('menu',{},{reload : true});
				}else{
					$state.go('login', {}, {
						reload : true
					});
				}
			},function () {
			});
	}

	synth.cancel();
	reproduzirFrase(getAudio.principal.intro);
	comecarReconhecimento();

	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript.trim();
				console.log('Som externo: ' + son);
				if (son == 'notícia') {
					currentNoticia = 1;
					reproduzirNoticia();
				} else if (son == 'repetir' && currentNoticia != 0) {
					reproduzirNoticia();
				} else if (son == 'próximo' && currentNoticia != 0) {
					if(currentNoticia > $scope.noticias.length){
						reproduzirFrase(getAudio.principal.ultimaNoticia);
					}else {
						++currentNoticia;
						reproduzirNoticia();
					}
				} else if (son == 'anterior' && currentNoticia != 0) {
					if(currentNoticia > 1){
						--currentNoticia;
						reproduzirNoticia();
					}else {
						reproduzirFrase(getAudio.principal.primeiraNoticia);
					}
				} else if (son == 'principal') {
					synth.cancel();
					$state.go('principal',{},{reload : true});
				} else if (son == 'contato') {
					synth.cancel();
					$state.go('contato',{},{reload : true});
				} else if (son == 'localização') {
					synth.cancel();
					$state.go('localizacao',{},{reload : true});
				} else if (son == 'login') {
					synth.cancel();
					$state.go('login',{},{reload : true});
				} else if (son == 'outros') {
					reproduzirFrase(getAudio.principal.fraseAjuda);
				} else if (son == 'ajuda') {
					reproduzirFrase(getAudio.principal.intro + getAudio.principal.fraseAjuda);
				} else {
					reproduzirFrase(getAudio.ajuda);
				}
			}
		}
	};

	function reproduzirNoticia () {
		if (!$scope.noticias){
			reproduzirFrase(getAudio.principal.semNoticia);
			currentNoticia = 0;
		}else if(currentNoticia > $scope.noticias.length){
			reproduzirFrase(getAudio.principal.ultimaNoticia);
			--currentNoticia;
		}else if ($scope.noticias){
			document.getElementById("noticia"+currentNoticia).focus();
			var descricaoImagem = "";
			if($scope.noticias[currentNoticia - 1].descricaoImagem){
				descricaoImagem = ", Descrição da imagem da Notícia: " + $scope.noticias[currentNoticia - 1].descricaoImagem;
			}
			let data =  Date.parse($scope.noticias[currentNoticia - 1].dataPublicacao);
			reproduzirFrase(" Título: " + $scope.noticias[currentNoticia - 1].titulo + ', publicada por ' + $scope.noticias[currentNoticia - 1].usuario +
			descricaoImagem + ", Corpo: " + htmlToPlaintext($scope.noticias[currentNoticia- 1].corpo));
		} else {
			reproduzirFrase(getAudio.principal.semNoticia);
			currentNoticia = 0;
		}
	}

}]);
