export const passwordCheck = (password) => {
    if(password === '') {
        return {
            isValid: false,
            message: '비밀번호를 입력하세요.'
        };
    } else if(password.length < 8 || password.length > 20) {
        return {
            isValid: false,
            message: '비밀번호는 8~20자로 입력하세요.'
        }
    } else if(!password.match(/^(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$/)) {
        return {
            isValid: false,
            message: '비밀번호는 하나 이상의 대문자와 특수문자를 포함해야 합니다.'
        }
    } else {
        return {
            isValid: true,
            message: ''
        }
    }
}

export function samePasswordCheck(password, samePassword) {
    if(password !== samePassword) {
        return {
            isValid: false,
            message: '비밀번호가 일치하지 않습니다.'
        };
    } else {
        return {
            isValid: true,
            message: ''
        }
    }
}