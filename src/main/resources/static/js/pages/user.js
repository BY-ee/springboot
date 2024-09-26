$(document).ready(function() {
    $('#change').attr('disabled', true);
    
    $('#password, #password-check').on('keyup', function() {
        let password = $('#password').val();
        let passwordCheck = $('#password-check').val();
        if (password.length == 0 || passwordCheck.length == 0) {
            $('.check-message').text('');
            $('#change').attr('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');
        } else if (password !== passwordCheck) {
            $('.check-message').text('비밀번호가 일치하지 않습니다.').css(
                'color', 'red'
            );
            $('#change').attr('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');
        } else {
            $('.check-message').text('비밀번호가 확인되었습니다.').css(
                'color', 'white'
            );
            $('#change').attr('disabled', false).removeClass('btn-primary-disabled').addClass('btn-primary');
        }
    });
});