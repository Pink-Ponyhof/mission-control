/**
 * The view of mission control.
 */
(function ($) {

    'use strict';

    /**
     * The view of the comment area in the detailed view of a development.
     * It has a callback for the addComment click action which is inititialized in the controller's constructor.
     * @constructor
     */
    mc.views.McView = function () {
        var callbacks = {
            onSwitchToConfig: function () {
            },
            onSwitchToOverview: function () {
            },
            onPostConfig: function () {
            }
        };

        /**
         * Sets the callback with given name to the given function.
         * @param name callback to be set
         * @param fn function assigned to the callback method
         */
        this.setCallback = function (name, fn) {
            if (typeof name !== 'string' || !$.isFunction(fn)) {
                throw new TypeError('invalid arguments');
            }

            switch (name) {
                case 'onSwitchToConfig':
                    callbacks.onSwitchToConfig = fn;
                    break;
                case 'onSwitchToOverview':
                    callbacks.onSwitchToOverview = fn;
                    break;
                case 'onPostConfig':
                    callbacks.onPostConfig = fn;
                    break;
                default:
                    throw new TypeError('invalid callback name: ' + name);
            }
        };

        this.onSwitchToConfigCallback = function () {
            return callbacks['onSwitchToConfig'];
        };

        this.getOnSwitchToOverviewCallback = function () {
            return callbacks['onSwitchToOverview'];
        };

        this.getOnPostConfigCallback = function () {
            return callbacks['onPostConfig'];
        };
    };

    mc.views.McView.prototype.displayOverview = function (data) {
        var onSwitchToConfigCallback = this.onSwitchToConfigCallback();
        $('#header').html('Configuration Overview');
        var container = $('#container');
        container.empty();
        $.each(data, function (i, conf) {

            var anchor = $('<a>').text(conf.name).attr('href', '#').click(function () {
                onSwitchToConfigCallback(conf.name)
            });
            console.log(conf);
            container.append('<p>');
            container.append(anchor);
            container.append('</p>');
        });
        console.log(data);
    };

    mc.views.McView.prototype.displayConfiguration = function (configName, data) {
        var postConfiguration = this.postConfiguration;
        var onSwitchToOverviewCallback = this.getOnSwitchToOverviewCallback();
        var onPostConfigCallback = this.getOnPostConfigCallback();

        $('#header').html(configName);
        var container = $('#container');
        container.empty();
        var form = $('<form>').attr('id', 'configForm');

        $.each(data.configValues, function (key, value) {
            var div = $('<div>');
            div.append($('<label>').text(key).attr('for', 'input_' + key));
            div.append($('<input type="text" class="configInput">').attr('id', 'input_' + key).attr('value', value));
            form.append(div);
        });

        form.append($('<input type="button">').attr('value', 'submit').click(function () {
            var data = {};
            $('.configInput').each(function (i, val) {
                val = $(val);
                var id = val.attr('id');
                var key = id.substr(id.indexOf('_') + 1);
                data[key] = val.val();
            });
            onPostConfigCallback(configName, data);
            console.log(data);
        }));
        container.append(form);
        container.append('<p>');
        container.append($('<a>').text('back').attr('href', '#').click(function () {
            onSwitchToOverviewCallback()
        }));
        container.append('</p>');
        console.log(data);
    };

    mc.views.McView.prototype.postConfiguration = function (configName) {

    }


}(jQuery));
