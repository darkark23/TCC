'use strict';
angular.module('tccApp').controller('LedorAulasController',
		[ "$scope", '$state', 'ledorAulasService', '$rootScope', 'loginService', function($scope, $state, ledorAulasService, $rootScope, loginService) {

	var construir = function (){
		loginService.confirmarUsuario($scope.dados,
			function (usuarioPerfil) {
				if (usuarioPerfil.existente == true){
					$rootScope.usuarioPerfil = usuarioPerfil;
					if ($state.params.data){
						ledorAulasService.agendaDiaEdicao({usuario:usuarioPerfil.login,data:new Date($state.params.data).toUTCString()},function (agendaDia) {
							$scope.agendaDia = agendaDia;
							$scope.dataSelecionada = new Date($state.params.data);
						},function () {
						})
					}else {
						ledorAulasService.agendaDiaEdicao({usuario:usuarioPerfil.login,data:new Date().toUTCString()},function (agendaDia) {
							$scope.agendaDia = agendaDia;
							$scope.dataSelecionada = new Date();
						},function () {
						})
					}
				}else{}
			},function () {
			});

	}

	construir();

	ledorAulasService.getListaAssunto(function (lista) {
		$scope.listaAssunto = lista;
	},function () {

	})

	$scope.acessarAgendaProxima = function() {
		let proximaData = new Date($state.params.data);
		proximaData.setDate(proximaData.getDate() + 1);
		$state.go('aulaLedor',{data : proximaData},{reload : true});
	};

	$scope.acessarAgendaAnterior = function() {
		let proximaData = new Date($state.params.data);
		proximaData.setDate(proximaData.getDate() - 1);
		$state.go('aulaLedor',{data : proximaData},{reload : true});
	};

	$scope.acessarAgendaBusca = function() {
		let proximaData = new Date($scope.dataSelecionada);
		$state.go('aulaLedor',{data : proximaData},{reload : true});
	};

	$scope.modalMarcar = function (idHorario) {
		$scope.aula = {
			idHorario : idHorario,
			titulo:null,
			assunto:null,
			descricao:null
		}
	}

	$scope.salvarAula = function() {
		$scope.aula.login = $rootScope.usuarioPerfil.login;
		$scope.aula.data = new Date($state.params.data).toUTCString();
		ledorAulasService.saveAula($scope.aula,function (retorno) {
			let proximaData = new Date($state.params.data);
			construir();
		},function () {
			let proximaData = new Date($state.params.data);
			construir();
		});
	};

	$scope.recaregarLista = function() {

	};

	$scope.cancelar = function(id) {
		ledorAulasService.cancelarAula(id,function (retorno) {
			if(retorno == 0){
				construir();
			}else{
				construir();
				console.log('Erro no cancelamento da aula');
			}
		},function () {
			construir();
			console.log('Erro no cancelamento da aula');
		})
	};

}]);
