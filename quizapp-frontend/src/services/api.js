import axios from 'axios';

// ======================================================
// BASE CONFIGURATION
// ======================================================

const BASE_URL =
  process.env.REACT_APP_API_URL || 'http://localhost:8762';

const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 10000,
});

// ======================================================
// RESPONSE INTERCEPTOR
// ======================================================

api.interceptors.response.use(
  (response) => response,

  (error) => {
    const message =
      error.response?.data?.message ||
      error.response?.data ||
      error.message ||
      'Something went wrong';

    console.error('API Error:', message);

    return Promise.reject({
      ...error,
      displayMessage: message,
    });
  }
);

// ======================================================
// QUESTION APIs
// ======================================================

export const questionApi = {

  // GET ALL QUESTIONS
  getAll: () =>
    api.get('/question/allQuestions'),

  // GET QUESTIONS BY CATEGORY
  getByCategory: (category) =>
    api.get(`/question/category/${category}`),

  // ADD QUESTION
  add: (question) =>
    api.post('/question/addQuestion', question),

  // UPDATE QUESTION
  update: (question) =>
    api.put('/question/updateQuestion', question),

  // DELETE QUESTION
  delete: (id) =>
    api.delete(`/question/deleteQuestion/${id}`),
};

// ======================================================
// QUIZ APIs
// ======================================================

export const quizApi = {

  // CREATE QUIZ
  create: (categoryName, numQuestions, title) =>
    api.post('/quiz/create', {
      categoryName,
      numQuestions,
      title,
    }),

  // GET QUIZ QUESTIONS
  getQuizQuestions: (id) =>
    api.get(`/quiz/getQuiz/${id}`),

  // SUBMIT QUIZ
  submit: (id, responses) =>
    api.post(`/quiz/submit/${id}`, responses),
};

// ======================================================
// EXPORT DEFAULT
// ======================================================

export default api;