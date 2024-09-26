export const userIdCheck = (userId) => {
    if(userId === '') {
        return {
            isValid: false,
            message: '아이디를 입력하세요.'
        }
    } else if(userId.length < 4 || userId.length > 15) {
        return {
            isValid: false,
            message: '아이디는 4~15자로 입력하세요.'
        }
    } else if(!userId.match(/^[\w]+$/)) {
        return {
            isValid: false,
            message: '아이디는 알파벳, 숫자, _만 입력할 수 있습니다.'
        }
    } else {
        return {
            isValid: true,
            message: ''
        }
    }
}