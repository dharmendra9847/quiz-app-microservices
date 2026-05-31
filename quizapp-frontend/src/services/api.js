import axios from 'axios';

const BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const api = axios.create({
  baseURL: BASE_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: 10000,
});

api.interceptors.response.use(
  (res) => res,
  (err) => {
    const message = err.response?.data?.message || err.message || 'Something went wrong';
    return Promise.reject({ ...err, displayMessage: message });
  }
);

// ── Question Endpoints ──────────────────────────────────────────────
export const questionApi = {
  getAll: () => api.get('/question/allQuestions'),
  getByCategory: (category) => api.get(`/question/category/${category}`),
  add: (question) => api.post('/question/addQuestion', question),
  update: (question) => api.put('/question/updateQuestion', question),
  delete: (id) => api.delete(`/question/deleteQuestion/${id}`),
};

// ── Quiz Endpoints ──────────────────────────────────────────────────
export const quizApi = {
  create: (category, numQ, title) =>
    api.post('/quiz/create', null, { params: { category, numQ, title } }),
  getQuizQuestions: (id) => api.get(`/quiz/getQuiz/${id}`),
  submit: (id, responses) => api.post(`/quiz/submit/${id}`, responses),
};

export default api;
