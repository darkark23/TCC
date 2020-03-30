'use strict'
angular.module('tccApp').service('loginService', ['Restangular','$state', function (Restangular,$state) {

    this.api = function () {
        return Restangular.one('login');
    };

    this.confirmarUsuario = function (usuarioConfirmacao ,success, error) {
        return this.api().one('verificarUsuario').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', usuarioConfirmacao).then(success, error);
    };

    this.logOff = function (success, error) {
        return this.api().one('logOff').get().then(success, error);
    };

    this.logOffUsuario = function () {
        this.logOff(
            function () {
                $state.go('principal', {}, {reload : true});
            },function () {
                $state.go('principal', {}, {reload : true});
            });
    }

}]);
