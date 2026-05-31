import React, { useState, useEffect, useCallback } from 'react';
import { useQuiz } from '../context/QuizContext';
import { quizApi } from '../services/api';
import Button from '../components/Button';
import toast from 'react-hot-toast';
import './QuizPage.css';

const QuizPage = () => {
  const { state, dispatch, setAnswer, nextQuestion, prevQuestion, jumpToQuestion, resetQuiz } = useQuiz();
  const { activeQuiz, quizLoading } = state;
  const { id, title, category, questions, answers, currentIndex, timeStarted } = activeQuiz;

  const [elapsed, setElapsed] = useState(0);
  const [submitting, setSubmitting] = useState(false);
  const [showConfirm, setShowConfirm] = useState(false);
  const [selectedAnim, setSelectedAnim] = useState(null);

  const currentQ = questions[currentIndex];
  const options = currentQ ? [currentQ.option1, currentQ.option2, currentQ.option3, currentQ.option4] : [];
  const totalAnswered = Object.keys(answers).length;
  const progressPct = (totalAnswered / questions.length) * 100;

  // Timer
  useEffect(() => {
    const interval = setInterval(() => {
      setElapsed(Math.floor((Date.now() - timeStarted) / 1000));
    }, 1000);
    return () => clearInterval(interval);
  }, [timeStarted]);

  const formatTime = (secs) => {
    const m = Math.floor(secs / 60).toString().padStart(2, '0');
    const s = (secs % 60).toString().padStart(2, '0');
    return `${m}:${s}`;
  };

  const handleSelect = useCallback((option) => {
    setSelectedAnim(option);
    setTimeout(() => setSelectedAnim(null), 300);
    setAnswer(currentQ.id, option);
  }, [currentQ, setAnswer]);

  const handleSubmit = async () => {
    setShowConfirm(false);
    setSubmitting(true);
    const timeTaken = Math.floor((Date.now() - timeStarted) / 1000);

    // Build responses: { id, response } matching ResponseDto
    const responses = questions.map(q => ({
      id: q.id,
      response: answers[q.id] || '',
    }));

    try {
      const res = await quizApi.submit(id, responses);
      const score = res.data;
      dispatch({
        type: 'SET_RESULT',
        payload: { score, total: questions.length, timeTaken },
      });
      toast.success('Quiz submitted!');
    } catch (err) {
      toast.error(err.displayMessage || 'Failed to submit quiz');
    } finally {
      setSubmitting(false);
    }
  };

  if (quizLoading) return (
    <div className="quiz-loading">
      <div className="quiz-loading__spinner" />
      <p>Loading your quiz...</p>
    </div>
  );

  if (!currentQ) return null;

  return (
    <div className="quiz-page page-enter">
      {/* Header */}
      <div className="quiz-header">
        <div className="container quiz-header__inner">
          <div className="quiz-header__info">
            <h2 className="quiz-header__title">{title}</h2>
            <span className="quiz-header__category">{category}</span>
          </div>
          <div className="quiz-header__meta">
            <div className="quiz-meta-item">
              <span className="quiz-meta-item__icon">⏱</span>
              <span className="quiz-meta-item__value">{formatTime(elapsed)}</span>
            </div>
            <div className="quiz-meta-item">
              <span className="quiz-meta-item__icon">✓</span>
              <span className="quiz-meta-item__value">{totalAnswered}/{questions.length}</span>
            </div>
            <Button variant="ghost" size="sm" onClick={resetQuiz}>✕ Exit</Button>
          </div>
        </div>
        {/* Progress bar */}
        <div className="quiz-progress-bar">
          <div
            className="quiz-progress-bar__fill"
            style={{ width: `${progressPct}%` }}
          />
        </div>
      </div>

      <div className="quiz-body container">
        <div className="quiz-main">
          {/* Question card */}
          <div className="question-card glass-card animate-scale-in" key={currentQ.id}>
            <div className="question-card__header">
              <span className="question-card__num">
                Question {currentIndex + 1} <span className="question-card__total">/ {questions.length}</span>
              </span>
              <span className={`question-card__status ${answers[currentQ.id] ? 'question-card__status--answered' : ''}`}>
                {answers[currentQ.id] ? '✓ Answered' : 'Unanswered'}
              </span>
            </div>

            <h2 className="question-card__text">{currentQ.questionTitle}</h2>

            <div className="options-grid">
              {options.map((opt, idx) => {
                const letters = ['A', 'B', 'C', 'D'];
                const isSelected = answers[currentQ.id] === opt;
                const isAnim = selectedAnim === opt;
                return (
                  <button
                    key={idx}
                    className={`option ${isSelected ? 'option--selected' : ''} ${isAnim ? 'option--pulse' : ''}`}
                    onClick={() => handleSelect(opt)}
                  >
                    <span className="option__letter">{letters[idx]}</span>
                    <span className="option__text">{opt}</span>
                    {isSelected && <span className="option__check">✓</span>}
                  </button>
                );
              })}
            </div>

            <div className="question-card__nav">
              <Button variant="secondary" onClick={prevQuestion} disabled={currentIndex === 0}>
                ← Prev
              </Button>
              <span className="question-card__dots">
                {questions.map((_, i) => (
                  <button
                    key={i}
                    className={`q-dot ${i === currentIndex ? 'q-dot--current' : ''} ${answers[questions[i]?.id] ? 'q-dot--answered' : ''}`}
                    onClick={() => jumpToQuestion(i)}
                  />
                ))}
              </span>
              {currentIndex < questions.length - 1 ? (
                <Button variant="primary" onClick={nextQuestion}>
                  Next →
                </Button>
              ) : (
                <Button variant="primary" onClick={() => setShowConfirm(true)} loading={submitting}>
                  Submit Quiz
                </Button>
              )}
            </div>
          </div>
        </div>

        {/* Sidebar: Question Navigator */}
        <div className="quiz-sidebar">
          <div className="q-nav glass-card">
            <h3 className="q-nav__title">Question Navigator</h3>
            <div className="q-nav__legend">
              <span className="legend-dot legend-dot--answered" /> Answered
              <span className="legend-dot legend-dot--current" /> Current
              <span className="legend-dot" /> Skipped
            </div>
            <div className="q-nav__grid">
              {questions.map((q, i) => (
                <button
                  key={i}
                  className={`q-nav__btn ${i === currentIndex ? 'q-nav__btn--current' : ''} ${answers[q.id] ? 'q-nav__btn--answered' : ''}`}
                  onClick={() => jumpToQuestion(i)}
                >
                  {i + 1}
                </button>
              ))}
            </div>
            <div className="q-nav__stats">
              <div className="q-nav__stat">
                <span className="q-nav__stat-val" style={{ color: 'var(--accent-green)' }}>{totalAnswered}</span>
                <span className="q-nav__stat-label">Answered</span>
              </div>
              <div className="q-nav__stat">
                <span className="q-nav__stat-val" style={{ color: 'var(--accent-red)' }}>{questions.length - totalAnswered}</span>
                <span className="q-nav__stat-label">Remaining</span>
              </div>
            </div>
            <Button
              variant={totalAnswered === questions.length ? 'primary' : 'outline'}
              fullWidth
              onClick={() => setShowConfirm(true)}
              loading={submitting}
            >
              Submit Quiz
            </Button>
          </div>
        </div>
      </div>

      {/* Confirm Modal */}
      {showConfirm && (
        <div className="modal-overlay" onClick={() => setShowConfirm(false)}>
          <div className="modal glass-card animate-scale-in" onClick={e => e.stopPropagation()}>
            <h3 className="modal__title">Submit Quiz?</h3>
            <p className="modal__body">
              You've answered <strong>{totalAnswered}</strong> out of <strong>{questions.length}</strong> questions.
              {questions.length - totalAnswered > 0 && (
                <span className="modal__warning"> {questions.length - totalAnswered} questions are unanswered.</span>
              )}
            </p>
            <div className="modal__actions">
              <Button variant="secondary" onClick={() => setShowConfirm(false)}>Go Back</Button>
              <Button variant="primary" onClick={handleSubmit} loading={submitting}>Confirm Submit</Button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default QuizPage;
