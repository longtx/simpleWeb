import Vue from 'vue';
import Vuex from 'vuex';
import parameter from './modules/parameter';
import getters from './getters';

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    parameter
  },
  getters
});

export default store
