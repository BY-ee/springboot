import { userIdCheck } from '../validations/userIdCheck.js';
import { passwordCheck, samePasswordCheck } from '../validations/passwordCheck.js';
import { emailCheck } from '../validations/emailCheck.js';
import { nicknameCheck } from '../validations/nicknameCheck.js';

$(() => {
    $('#signup-btn').prop('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');

    const messageField = [
        $('#userid-message'),
        $('#password-message'),
        $('#same-password-message'),
        $('#email-message'),
        $('#nickname-message')
    ]

    const clearInvalidMessage = () => {
        messageField.forEach(message => {
            message.text('');
        })
    }

    const invaildMessage = (area, message) => {
        area.text(message);
    }

    $('#userid').on('input', () => {
        const userId = $('#userid').val();
        const userIdResult = userIdCheck(userId);
        clearInvalidMessage();

        if(userIdResult.isValid) {
            $('#password').trigger('input');
        } else {
            invaildMessage($('#userid-message'), userIdResult.message);
            $('#signup-btn').prop('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');
        }
    });

    $('#password').on('input', () => {
        const password = $('#password').val();
        const passwordResult = passwordCheck(password);
        clearInvalidMessage();

        if(passwordResult.isValid) {
            $('#same-password').trigger('input');
        } else {
            invaildMessage($('#password-message'), passwordResult.message);
            $('#signup-btn').prop('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');
        }
    })

    $('#same-password').on('input', () => {
        const password = $('#password').val();
        const samePassword = $('#same-password').val();
        const samePasswordResult = samePasswordCheck(password, samePassword);
        clearInvalidMessage();

        if(samePasswordResult.isValid) {
            $('#email').trigger('input');
        } else {
            invaildMessage($('#same-password-message'), samePasswordResult.message);
            $('#signup-btn').prop('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');
        }
    })

    $('#email').on('input', () => {
        const email = $('#email').val();
        const emailResult = emailCheck(email);
        clearInvalidMessage();

        if(emailResult.isValid) {
            $('#nickname').trigger('input');
        } else {
            invaildMessage($('#email-message'), emailResult.message);
            $('#signup-btn').prop('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');
        }
    })

    $('#nickname').on('input', () => {
        const nickname = $('#nickname').val();
        const nicknameResult = nicknameCheck(nickname);
        clearInvalidMessage();

        if(nicknameResult.isValid) {
            $('#signup-btn').prop('disabled', false).removeClass('btn-primary-disabled').addClass('btn-primary');
            $('#nickname-message').text('');
        } else {
            invaildMessage($('#nickname-message'), nicknameResult.message);
            $('#signup-btn').prop('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');
        }
    })
})