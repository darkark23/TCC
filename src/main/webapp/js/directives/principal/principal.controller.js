'use strict'
angular.module('tccApp').controller('PrincipalController',
	["$scope",'$state','principalService','$rootScope',function($scope, $state, principalService, $rootScope) {

	var currentNoticia = 0;
	$scope.noticias = null;

	principalService.getNoticias(function (listaNoticias) {
		$scope.noticias = listaNoticias;
	},function () {
		alert(getTexto.principal.erroNoticia);
	});

	//Servico de voz
	synth.cancel();
	reproduzirFrase(getAudio.principal.intro);
	comecarReconhecimento();

	//Comando Teclado
	document.onkeyup = function(e) {
		if (e.which == 96) {
			reproduzirFrase(getAudio.principal.intro);
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
			reproduzirFrase(getAudio.principal.fraseAjuda);
		}
	};

	//Reconhecimento
	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript.trim();
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
				}else if (son == 'notícia') {
					currentNoticia = 1;
					reproduzirNoticia();
				} else if (son == 'outros' || son == 'ajuda') {
					reproduzirFrase(getAudio.principal.fraseAjuda);
				} else if (son == 'repetir' && currentNoticia != 0) {
					reproduzirNoticia();
				} else if (son == 'próxima' && currentNoticia != 0) {
					++currentNoticia;
					reproduzirNoticia();
				} else if (son == 'anterior' && currentNoticia != 0) {
					if(currentNoticia > 1){
						--currentNoticia;
						reproduzirNoticia();
					}
				} else {
					reproduzirFrase(getAudio.principal.fraseAjuda);
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
				descricaoImagem = "Descrição imagem: " + $scope.noticias[currentNoticia - 1].descricaoImagem;
			}
			reproduzirFrase(+ " Título: " + $scope.noticias[currentNoticia - 1].titulo
			+ " Imagem Notícia: " + descricaoImagem
			+ " Corpo: " + htmlToPlaintext($scope.noticias[currentNoticia- 1].corpo));
		} else {
			reproduzirFrase(getAudio.principal.semNoticia);
			currentNoticia = 0;
		}
	}

}]);
