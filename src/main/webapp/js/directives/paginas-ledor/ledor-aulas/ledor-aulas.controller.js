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

							if($state.params.data){
								$scope.dataSelecionada = new Date($state.params.data);
							}else{
								$scope.dataSelecionada = new Date();
							}

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
		let proximaData;
		if($state.params.data){
			proximaData = new Date($state.params.data);
		}else{
			proximaData = new Date();
		}

		proximaData.setDate(proximaData.getDate() + 1);
		$state.go('aulaLedor',{data : proximaData},{reload : true});
	};

	$scope.acessarAgendaAnterior = function() {
		let proximaData;
		if($state.params.data){
			proximaData = new Date($state.params.data);
		}else{
			proximaData = new Date();
		}

		proximaData.setDate(proximaData.getDate() - 1);
		$state.go('aulaLedor',{data : proximaData},{reload : true});
	};

	$scope.acessarAgendaBusca = function() {
		let proximaData = new Date($scope.dataSelecionada);
		$state.go('aulaLedor',{data : proximaData},{reload : true});
	};

	$scope.modalMarcar = function (aula) {
		if (aula.id){
			$scope.aula = {
				id:aula.id,
				idHorario : aula.horarioId,
				titulo:aula.nome,
				assunto:aula.assunto,
				descricao:aula.descricaoAula,
				idAprovado: aula.idAprovado,
				motivoRejeicao: aula.motivoRejeicao
			}
		}else {
			$scope.aula = {
				idHorario : aula.horarioId,
				titulo:null,
				assunto:null,
				descricao:null
			}
		}

	}

	$scope.modalRejeitar = function (id) {
		$scope.aula = {
			id : id,
			titulo:null,
			assunto:null,
			descricao:null,
			rejeicao:null
		}
	}

		$scope.modalCancelar = function (id) {
			$scope.aula = {
				id : id,
				titulo:null,
				assunto:null,
				descricao:null,
			}
		}

	$scope.modalAprovar = function (id) {
		$scope.aula = {
			id : id,
			titulo:null,
			assunto:null,
			descricao:null
		}
	}

	$scope.modalVisualizar = function (aula) {
		$scope.aula = {
			titulo: aula.nome,
			assunto: aula.assunto.nome,
			descricao: aula.descricaoAula,
			idAprovado: aula.idAprovado,
			motivoRejeicao: aula.motivoRejeicao
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

	$scope.cancelar = function() {

		ledorAulasService.cancelarAula($scope.aula.id,function (retorno) {
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

	$scope.aprovar = function(id) {
		ledorAulasService.aprovarAula($scope.aula.id,function (retorno) {
			if(retorno == 0){
				construir();
			}else{
				construir();
				console.log('Erro na aprovação da aula');
			}
		},function () {
			construir();
			console.log('Erro no aprovação da aula');
		})
	};

	$scope.rejeitar = function() {
		ledorAulasService.rejeitarAula($scope.aula,function (retorno) {
			if(retorno == 0){
				construir();
			}else{
				construir();
				console.log('Erro na rejeição da aula');
			}
		},function () {
			construir();
			console.log('Erro no rejeição da aula');
		})
	};

	$scope.logOff = function() {
		loginService.logOffUsuario();
	};

}]);
