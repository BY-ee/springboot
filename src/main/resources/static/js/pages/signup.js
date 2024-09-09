import { userIdCheck } from '../validations/userIdCheck.js';
import { passwordCheck } from '../validations/passwordCheck.js';

$(() => {
    $('#signup-btn').prop('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');

    $('#userid').on('input', () => {
        const userId = $('#userid').val();
        const userIdResult = userIdCheck(userId);
        $('#userid-message').text(userIdResult.message);

        if(userIdResult.isValid) {
            $('#signup-btn').prop('disabled', false).removeClass('btn-primary-disabled').addClass('btn-primary');
        } else {
            $('#signup-btn').prop('disabled', true).removeClass('btn-primary').addClass('btn-primary-disabled');
        }
    });
})