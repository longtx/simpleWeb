import fetch from '@/utils/fetch';

export function check(phone) {
    return fetch({
        url: '/sign/validate',
        method: 'get',
        params: { phone }
    });
};

export function save(obj) {
    return fetch({
        url: '/sign/save',
        method: 'post',
        data: obj
    });
};
