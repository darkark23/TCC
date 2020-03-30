'use strict'
angular.module('tccApp').service('adminListaUsuarioService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('usuario');
    };

    this.listarUsuariosAtivos = function (success, error) {
        let lista = this.api().one('listarUsuariosAtivos').get().then(success, error);
        return lista;
    }

    this.desativarUsuario = function (id, success, error) {
        let resposta = this.api().one('desativarUsuario/' + id).get().then(success, error);
        return resposta;
    }

    this.aprovarUsuario = function (id, success, error) {
        let resposta = this.api().one('aprovarUsuario/' + id).get().then(success, error);
        return resposta;
    }

    this.rejeitarUsuario = function (usuarioDTORequest ,success, error) {
        return this.api().all('rejeitarUsuario').post(usuarioDTORequest).then(success, error);

    };

}]);
