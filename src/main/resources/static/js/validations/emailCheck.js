export const emailCheck = (email) => {
    if(email === '') {
        return {
            isValid: false,
            message: '이메일을 입력하세요.'
        }
    } else if(email.length > 40) {
        return {
            isValid: false,
            message: '이메일은 40자 이내로 입력하세요.'
        }
    } else if(!email.match(/^[\w]+@[a-z0-9]([-a-z0-9]*[a-z0-9])?\.[a-z]{2,6}$/i)) {
        return {
            isValid: false,
            message: '올바른 이메일 형식이 아닙니다.'
        }
    } else {
        return {
            isValid: true,
            message: ''
        }
    }
}