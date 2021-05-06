const parameter = {
  state: {
    kenuo:''
  },
  mutations: {
    SET_KENUO: (state, kenuo) => {
      state.kenuo = kenuo
    }
  },
  actions: {
    isKenuo({
      commit
    }, isz) {
      return new Promise((resolve) => {
        if (isz === 'isz') {
          commit('SET_KENUO', false);
        } else {
          commit('SET_KENUO', true);
        }
        resolve();
      })
    }
  }
}

export default parameter;
