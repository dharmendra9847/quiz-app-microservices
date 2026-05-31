import React, { createContext, useContext, useReducer, useCallback } from 'react';

const QuizContext = createContext(null);

const initialState = {
  // Questions bank
  allQuestions: [],
  questionsLoading: false,
  questionsError: null,

  // Active quiz session
  activeQuiz: {
    id: null,
    title: '',
    category: '',
    questions: [],   // QuestionDto[]
    answers: {},     // { questionId: selectedOption }
    currentIndex: 0,
    timeStarted: null,
  },

  quizLoading: false,
  quizError: null,

  // Result
  result: {
    score: null,
    total: null,
    timeTaken: null,
  },

  // UI
  view: 'home', // home | questions | quiz | admin | result
};

function reducer(state, action) {
  switch (action.type) {
    case 'SET_QUESTIONS_LOADING':
      return { ...state, questionsLoading: action.payload, questionsError: null };
    case 'SET_QUESTIONS':
      return { ...state, allQuestions: action.payload, questionsLoading: false };
    case 'SET_QUESTIONS_ERROR':
      return { ...state, questionsError: action.payload, questionsLoading: false };

    case 'SET_QUIZ_LOADING':
      return { ...state, quizLoading: action.payload, quizError: null };
    case 'SET_QUIZ_STARTED':
      return {
        ...state,
        quizLoading: false,
        activeQuiz: {
          ...state.activeQuiz,
          id: action.payload.id,
          title: action.payload.title,
          category: action.payload.category,
          questions: action.payload.questions,
          answers: {},
          currentIndex: 0,
          timeStarted: Date.now(),
        },
        view: 'quiz',
      };
    case 'SET_QUIZ_ERROR':
      return { ...state, quizError: action.payload, quizLoading: false };

    case 'SET_ANSWER':
      return {
        ...state,
        activeQuiz: {
          ...state.activeQuiz,
          answers: { ...state.activeQuiz.answers, [action.payload.questionId]: action.payload.option },
        },
      };
    case 'NEXT_QUESTION':
      return {
        ...state,
        activeQuiz: {
          ...state.activeQuiz,
          currentIndex: Math.min(state.activeQuiz.currentIndex + 1, state.activeQuiz.questions.length - 1),
        },
      };
    case 'PREV_QUESTION':
      return {
        ...state,
        activeQuiz: {
          ...state.activeQuiz,
          currentIndex: Math.max(state.activeQuiz.currentIndex - 1, 0),
        },
      };
    case 'JUMP_QUESTION':
      return {
        ...state,
        activeQuiz: { ...state.activeQuiz, currentIndex: action.payload },
      };

    case 'SET_RESULT':
      return {
        ...state,
        result: {
          score: action.payload.score,
          total: action.payload.total,
          timeTaken: action.payload.timeTaken,
        },
        view: 'result',
      };

    case 'SET_VIEW':
      return { ...state, view: action.payload };

    case 'RESET_QUIZ':
      return {
        ...state,
        activeQuiz: { ...initialState.activeQuiz },
        result: { ...initialState.result },
        view: 'home',
      };

    default:
      return state;
  }
}

export function QuizProvider({ children }) {
  const [state, dispatch] = useReducer(reducer, initialState);

  const setView = useCallback((view) => dispatch({ type: 'SET_VIEW', payload: view }), []);
  const setAnswer = useCallback((questionId, option) =>
    dispatch({ type: 'SET_ANSWER', payload: { questionId, option } }), []);
  const nextQuestion = useCallback(() => dispatch({ type: 'NEXT_QUESTION' }), []);
  const prevQuestion = useCallback(() => dispatch({ type: 'PREV_QUESTION' }), []);
  const jumpToQuestion = useCallback((idx) => dispatch({ type: 'JUMP_QUESTION', payload: idx }), []);
  const resetQuiz = useCallback(() => dispatch({ type: 'RESET_QUIZ' }), []);

  return (
    <QuizContext.Provider value={{ state, dispatch, setView, setAnswer, nextQuestion, prevQuestion, jumpToQuestion, resetQuiz }}>
      {children}
    </QuizContext.Provider>
  );
}

export function useQuiz() {
  const ctx = useContext(QuizContext);
  if (!ctx) throw new Error('useQuiz must be used within QuizProvider');
  return ctx;
}
