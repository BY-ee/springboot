export const nicknameCheck = (nickname) => {
    if(nickname === '') {
        return {
            isValid: false,
            message: '닉네임을 입력하세요.'
        }
    } else if(nickname.length < 2 || nickname.length > 12) {
        return {
            isValid: false,
            message: '닉네임은 2~12자로 입력하세요.'
        }
    } else if(nickname.match(/^[A-Za-z0-9가-힣_]$/)) {
        return {
            isValid: false,
            message: '닉네임은 영문, 숫자, 한글, _만 입력할 수 있습니다.'
        }
    } else {
        return {
            isValid: true,
            message: ''
        }
    }
}