'use strict'
angular.module('tccApp').service('usuarioEdicaoService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('cadastro');
    };

    this.getListasCadastro = function (success, error) {
        return this.api().one('getListasCadastro').get().then(success, error);
    }

    this.getCadastroUsuario = function (id,success, error) {
        return this.api().one('getCadastroUsuario/' + id).get().then(success, error);
    }

    this.salvarUsuario = function (usuario ,success, error) {
        return this.api().all('salvar').post(usuario).then(success, error);
    };

}]);
