'use strict';
angular.module('tccApp').controller('AdminListaUsuarioController',
		[ "$scope", '$state', 'adminListaUsuarioService', '$rootScope', 'loginService', function($scope, $state, adminListaUsuarioService, $rootScope, loginService) {

		$scope.listarUsuarios = null;
		$rootScope.usuarioPerfil = null;
		$scope.modalMotivo = null;
		let rejeitarId = null;

		let construir = function (){
			verificarUsuarioLogado();
		}

		let construirListaUsuario = function () {
			adminListaUsuarioService.listarUsuariosAtivos(function (listaUsuario) {
				$scope.listarUsuarios = listaUsuario;
			},function () {});
		}

		let verificarUsuarioLogado = function () {
			loginService.confirmarUsuario($scope.dados, function (usuarioPerfil) {
					if (usuarioPerfil.existente == true){
						$rootScope.usuarioPerfil = usuarioPerfil;
						construirListaUsuario()	;
					}else{}
				},function () {});
		}

		construir();

		$scope.voltar = function() {
			$state.go('menu',{}, {reload : true});
		};

		$scope.aprovar = function(id) {
			adminListaUsuarioService.aprovarUsuario(id,function (resposta) {
				if(resposta == 0){
					construirListaUsuario();
				}
			},function () {});
		};

		$scope.desativar = function(id) {
			adminListaUsuarioService.desativarUsuario(id,function (resposta) {
				if(resposta == 0){
					construirListaUsuario();
				}
			},function () {});
		};

		$scope.rejeitar = function() {
			if(rejeitarId && $scope.modalMotivo) {
				let usuarioDTORequest = {
					id: rejeitarId,
					motivo: $scope.modalMotivo
				};
				adminListaUsuarioService.rejeitarUsuario(usuarioDTORequest,function (resposta) {
					if(resposta == 0){
						construirListaUsuario();
						$scope.limparRejeitar();
					}
				},function () {});
			}
		};

		$scope.prepararRejeitar = function(id) {
			rejeitarId = id;
		};

		$scope.limparRejeitar = function() {
			rejeitarId = null;
			$scope.modalMotivo = null;
		};

		$scope.visualizarUsuario = function(id) {
			$state.go('usuarioEdicao',{id : id}, {reload : true});
		};


}]);
