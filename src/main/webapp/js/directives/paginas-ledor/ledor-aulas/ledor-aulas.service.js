'use strict'
angular.module('tccApp').service('ledorAulasService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('aula');
    };

    this.getAgendaDia = function (data, success, error) {
        var agendaDia = this.api().one('agendaDia/' + data).get().then(success, error);
        return agendaDia;
    };

}]);
