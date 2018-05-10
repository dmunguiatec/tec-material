$(function() {
    app = {
        initEventHandlers: function() {
            $('form.profile-form')
                .bind('submit', function(e) {
                    e.preventDefault();

                    if (app.validator(this)) {
                        var username = $('.current-user-field').data('username'),
                            firstName = $('#firstName').val(),
                            lastName = $('#lastName').val(),
                            email = $('#email').val();

                        var request = {
                            username: username,
                            firstName: firstName,
                            lastName: lastName,
                            email: email
                        }

                        $.post('/api/user/profile', JSON.stringify(request), function(response) {
                            window.location = '/html/profile.html';
                        });
                    }
                });
        },

        initProfile: function() {
            // se debe utilizar un framework de autenticación
            var hardcodedUsername = 'diego';

            $.get('/api/user/' + hardcodedUsername, null, function(response) {
                var $currentUserField = $('.current-user-field');
                $currentUserField.html(response.username);
                $currentUserField.data('username', response.username);

                $('.profile-photo').attr('src', '/api/user/profile/photo/' + response.username);
                $('#firstName').val(response.firstName);
                $('#lastName').val(response.lastName);
                $('#email').val(response.email);
            });
        },

        validator: function(wrapper) {
            $('.invalid-val').removeClass('invalid-val');
            $.validity.start();

            // Not empty
            $('.required', wrapper).require();
            // Email address
            $('.email', wrapper).match('email');
            // Equal passwords
            $('input.dual-password', wrapper).require().equal();
            // Radio checked
            $('.check-req', wrapper).checkboxChecked();
            // URL
            $('.url-req', wrapper).require().match('url');
            // Long text
            $('.long-text', wrapper).maxLength(2048);
            // Short text
            $('.short-text', wrapper).maxLength(60);
            // US phone number
            $('.phone', wrapper).require().match('phone');
            // Alphanumeric
            $('.alphanumeric', wrapper).require().match('alphanumeric');
            // Number
            $('.number', wrapper).require().match('number');
            // Date
            $('.date', wrapper).require().match('date');

            var result = $.validity.end();
            return result.valid;
        },

        init: function() {
            this.initEventHandlers();
        }
    };

    app.init();
});