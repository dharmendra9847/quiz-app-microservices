import React, { useState } from 'react';
import { useQuiz } from '../context/QuizContext';
import { quizApi } from '../services/api';
import Button from '../components/Button';
import toast from 'react-hot-toast';
import './QuizSetupPage.css';

const CATEGORIES = ['Java', 'Python', 'JavaScript', 'SQL', 'Spring', 'React', 'HTML', 'CSS', 'DSA', 'DBMS'];
const NUM_OPTIONS = [5, 10, 15, 20, 25, 30];

const QuizSetupPage = () => {
  const { dispatch } = useQuiz();
  const [form, setForm] = useState({ category: '', numQ: 10, title: '' });
  const [loading, setLoading] = useState(false);
  const [step, setStep] = useState(1); // 1: category, 2: details

  const handleCategorySelect = (cat) => {
    setForm(f => ({ ...f, category: cat, title: `${cat} Quiz` }));
    setStep(2);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.category) return toast.error('Please select a category');
    if (!form.title.trim()) return toast.error('Please enter a quiz title');

    setLoading(true);
    dispatch({ type: 'SET_QUIZ_LOADING', payload: true });

    try {
      // Step 1: Create quiz → returns quiz ID
      const createRes = await quizApi.create(form.category, form.numQ, form.title);
      const quizId = createRes.data; // backend returns string like "Quiz Created Successfully: 3"
      // extract the ID from response if needed
      const idMatch = String(quizId).match(/\d+/);
      const extractedId = idMatch ? parseInt(idMatch[0]) : quizId;

      toast.success('Quiz created! Loading questions...');

      // Step 2: Fetch quiz questions → QuestionDto[]
      const questionsRes = await quizApi.getQuizQuestions(extractedId);
      const questions = questionsRes.data;

      if (!questions || questions.length === 0) {
        toast.error('No questions found for this category');
        setLoading(false);
        dispatch({ type: 'SET_QUIZ_ERROR', payload: 'No questions found' });
        return;
      }

      dispatch({
        type: 'SET_QUIZ_STARTED',
        payload: {
          id: extractedId,
          title: form.title,
          category: form.category,
          questions,
        },
      });
    } catch (err) {
      const msg = err.displayMessage || 'Failed to create quiz';
      toast.error(msg);
      dispatch({ type: 'SET_QUIZ_ERROR', payload: msg });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="quiz-setup page-enter">
      <div className="container">
        <div className="quiz-setup__header">
          <h1 className="quiz-setup__title">
            {step === 1 ? 'Choose a Category' : 'Configure Your Quiz'}
          </h1>
          <p className="quiz-setup__subtitle">
            {step === 1
              ? 'Select the subject you want to be tested on'
              : `You picked ${form.category}. Now set the details.`}
          </p>
          <div className="quiz-setup__steps">
            <div className={`setup-step ${step >= 1 ? 'setup-step--active' : ''}`}>
              <span className="setup-step__num">1</span>
              <span className="setup-step__label">Category</span>
            </div>
            <div className="setup-step__line" />
            <div className={`setup-step ${step >= 2 ? 'setup-step--active' : ''}`}>
              <span className="setup-step__num">2</span>
              <span className="setup-step__label">Details</span>
            </div>
          </div>
        </div>

        {step === 1 && (
          <div className="categories-select animate-scale-in">
            {CATEGORIES.map((cat, i) => (
              <button
                key={cat}
                className={`cat-btn ${form.category === cat ? 'cat-btn--selected' : ''}`}
                onClick={() => handleCategorySelect(cat)}
                style={{ animationDelay: `${i * 0.05}s` }}
              >
                <span className="cat-btn__check">✓</span>
                {cat}
              </button>
            ))}
          </div>
        )}

        {step === 2 && (
          <form className="quiz-form glass-card animate-scale-in" onSubmit={handleSubmit}>
            <button type="button" className="quiz-form__back" onClick={() => setStep(1)}>
              ← Back to categories
            </button>

            <div className="quiz-form__selected-cat">
              <span className="quiz-form__cat-badge">{form.category}</span>
              <span className="quiz-form__cat-label">Selected Category</span>
            </div>

            <div className="form-group">
              <label className="form-label">Quiz Title</label>
              <input
                type="text"
                className="form-input"
                value={form.title}
                onChange={(e) => setForm(f => ({ ...f, title: e.target.value }))}
                placeholder="e.g. Java Fundamentals Test"
                maxLength={80}
                required
              />
            </div>

            <div className="form-group">
              <label className="form-label">Number of Questions</label>
              <div className="num-options">
                {NUM_OPTIONS.map(n => (
                  <button
                    key={n}
                    type="button"
                    className={`num-option ${form.numQ === n ? 'num-option--selected' : ''}`}
                    onClick={() => setForm(f => ({ ...f, numQ: n }))}
                  >
                    {n}
                  </button>
                ))}
              </div>
            </div>

            <div className="quiz-form__summary">
              <div className="summary-item">
                <span className="summary-item__label">Category</span>
                <span className="summary-item__value">{form.category}</span>
              </div>
              <div className="summary-item">
                <span className="summary-item__label">Questions</span>
                <span className="summary-item__value">{form.numQ}</span>
              </div>
              <div className="summary-item">
                <span className="summary-item__label">Est. Time</span>
                <span className="summary-item__value">~{form.numQ * 1.5} min</span>
              </div>
            </div>

            <Button type="submit" size="lg" fullWidth loading={loading}>
              {loading ? 'Creating Quiz...' : 'Start Quiz →'}
            </Button>
          </form>
        )}
      </div>
    </div>
  );
};

export default QuizSetupPage;
