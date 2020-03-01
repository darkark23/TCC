'use strict'
angular.module('tccApp').service('ledorAulasService', ['Restangular', function (Restangular) {

    this.agendaDiaEdicao = function (dados ,success, error) {
        return Restangular.all('aula/agendaDiaEdicao').post(dados).then(success, error);
    };

    this.cancelarAula = function (id ,success, error) {
        return Restangular.all('aula/cancelarAula').post(id).then(success, error);
    };

}]);
