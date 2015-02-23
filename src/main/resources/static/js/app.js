angular.module('app', [ 'ngRoute' ]).config(function($routeProvider) {    // allow using lodash in views
    $routeProvider.when('/', {
        templateUrl : 'home.html',
        controller : 'home'
    }).when('/login', {
        templateUrl : 'login.html',
        controller : 'navigation'
    }).when('/products', {
        templateUrl : 'products.html',
        controller : 'products'
    }).when('/companies', {
        templateUrl : 'companies.html',
        controller : 'companies'
    }).when('/sales', {
        templateUrl : 'sales.html',
        controller : 'sales'
    }).otherwise('/');
}).controller('companies', function($scope, $http) {
    // load companies and their users
    $http.get('api/companies').success(function(data) {
        $scope.companies = _.sortBy(data, 'name');
    });
    $http.get('api/users').success(function(data) {
        $scope.users = _.sortBy(data, 'lastName');
    });
}).controller('home', function($scope, $http) {
}).controller('navigation', function($rootScope, $scope, $http, $location, $route) {
    // used to detect active tab
    $scope.tab = function(route) {
        return $route.current && route === $route.current.controller;
    };

    // this part could use more love, but it is out of scope
    $scope.authenticate = function() {
        // get permissions
        $http.get('api/permissions').success(function(data) {
            if (_.isObject(data)) {
                $rootScope.authenticated = true;
                $rootScope.roles = data;
            } else {
                $rootScope.authenticated = false;
            }
        });
    };

    $scope.logout = function() {
        // logout
        $rootScope.authenticated = false;
        $rootScope.role = null;
        $location.path("/logout");
    };

    // trigger authentication
    $scope.authenticate();
}).controller('products', function($scope, $http) {
    // load products
    $http.get('api/products').success(function(data) {
        $scope.products = _.sortBy(data, 'name');
    });
}).controller('sales', function($scope, $http) {
    $scope.activeProduct = null;

    // load products
    $http.get('api/products').success(function(data) {
        $scope.products = _.sortBy(data, 'name');
    });

    // triggered when a product is selected
    $scope.viewProductSales = function (product) {
        $scope.activeProduct = null;
        $scope.sales = null;

        $http.get('api/products/' + product.id + '/monthly-sales').success(function(data) {
            $scope.activeProduct = product;
            $scope.sales = _.sortBy(data, ['year', 'month']);
        });
    };
}).run(function($rootScope) {
    // allow lodash in views
    $rootScope._ = _;
});