'use strict'
angular.module('tccApp').service('loginService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('login');
    };

    this.confirmarUsuario = function (usuarioConfirmacao ,success, error) {
        var usuarioPerfil = this.api().one('verificarUsuario').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', usuarioConfirmacao).then(success, error);
        return usuarioPerfil;
    };

}]);
