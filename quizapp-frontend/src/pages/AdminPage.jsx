import React, { useState, useEffect, useCallback } from 'react';
import { questionApi } from '../services/api';
import Button from '../components/Button';
import toast from 'react-hot-toast';
import './AdminPage.css';

const EMPTY_FORM = {
  id: null,
  questionTitle: '',
  option1: '',
  option2: '',
  option3: '',
  option4: '',
  rightAnswer: '',
  difficultyLevel: 'Easy',
  category: '',
};

const DIFFICULTIES = ['Easy', 'Medium', 'Hard'];

const AdminPage = () => {
  const [questions, setQuestions] = useState([]);
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);
  const [deleting, setDeleting] = useState(null);
  const [form, setForm] = useState(EMPTY_FORM);
  const [editing, setEditing] = useState(false);
  const [showForm, setShowForm] = useState(false);
  const [filterCat, setFilterCat] = useState('');
  const [search, setSearch] = useState('');
  const [confirmDelete, setConfirmDelete] = useState(null);

  const fetchQuestions = useCallback(async () => {
    setLoading(true);
    try {
      const res = await questionApi.getAll();
      setQuestions(res.data || []);
    } catch {
      toast.error('Failed to load questions');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { fetchQuestions(); }, [fetchQuestions]);

  const handleFormChange = (field, value) => {
    setForm(f => ({ ...f, [field]: value }));
  };

  const validateForm = () => {
    const required = ['questionTitle', 'option1', 'option2', 'option3', 'option4', 'rightAnswer', 'category'];
    for (const f of required) {
      if (!form[f]?.trim()) {
        toast.error(`Please fill in: ${f}`);
        return false;
      }
    }
    const opts = [form.option1, form.option2, form.option3, form.option4];
    if (!opts.includes(form.rightAnswer)) {
      toast.error('Right answer must match one of the options');
      return false;
    }
    return true;
  };

  const handleSave = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
    setSaving(true);
    try {
      if (editing) {
        await questionApi.update(form);
        toast.success('Question updated!');
      } else {
        await questionApi.add(form);
        toast.success('Question added!');
      }
      setForm(EMPTY_FORM);
      setShowForm(false);
      setEditing(false);
      fetchQuestions();
    } catch (err) {
      toast.error(err.displayMessage || 'Save failed');
    } finally {
      setSaving(false);
    }
  };

  const handleEdit = (q) => {
    setForm({ ...q });
    setEditing(true);
    setShowForm(true);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleDelete = async (id) => {
    setDeleting(id);
    setConfirmDelete(null);
    try {
      await questionApi.delete(id);
      toast.success('Question deleted');
      setQuestions(prev => prev.filter(q => q.id !== id));
    } catch {
      toast.error('Delete failed');
    } finally {
      setDeleting(null);
    }
  };

  const categories = [...new Set(questions.map(q => q.category).filter(Boolean))];
  const filtered = questions.filter(q => {
    const matchCat = !filterCat || q.category?.toLowerCase() === filterCat.toLowerCase();
    const matchSearch = !search || q.questionTitle?.toLowerCase().includes(search.toLowerCase());
    return matchCat && matchSearch;
  });

  const diffColor = { Easy: 'var(--accent-green)', Medium: 'var(--accent-yellow)', Hard: 'var(--accent-red)' };

  return (
    <div className="admin-page page-enter">
      <div className="container">
        <div className="admin-header">
          <div>
            <h1 className="admin-title">Question <span className="gradient-text">Manager</span></h1>
            <p className="admin-subtitle">Manage all quiz questions — add, edit, delete</p>
          </div>
          <Button
            size="md"
            onClick={() => { setForm(EMPTY_FORM); setEditing(false); setShowForm(!showForm); }}
            variant={showForm ? 'secondary' : 'primary'}
          >
            {showForm ? '✕ Close Form' : '+ Add Question'}
          </Button>
        </div>

        {/* Add/Edit Form */}
        {showForm && (
          <div className="admin-form-wrap glass-card animate-slide-up">
            <h2 className="admin-form-title">{editing ? '✎ Edit Question' : '+ New Question'}</h2>
            <form className="admin-form" onSubmit={handleSave}>
              <div className="admin-form__row admin-form__row--full">
                <label className="form-label">Question Title *</label>
                <textarea
                  className="form-input form-textarea"
                  value={form.questionTitle}
                  onChange={e => handleFormChange('questionTitle', e.target.value)}
                  placeholder="Enter your question here..."
                  rows={3}
                  required
                />
              </div>

              <div className="admin-form__grid">
                {['option1', 'option2', 'option3', 'option4'].map((opt, i) => (
                  <div key={opt} className="admin-form__row">
                    <label className="form-label">Option {i + 1} *</label>
                    <input
                      type="text"
                      className="form-input"
                      value={form[opt]}
                      onChange={e => handleFormChange(opt, e.target.value)}
                      placeholder={`Option ${i + 1}`}
                      required
                    />
                  </div>
                ))}
              </div>

              <div className="admin-form__row">
                <label className="form-label">Correct Answer *</label>
                <select
                  className="form-input form-select"
                  value={form.rightAnswer}
                  onChange={e => handleFormChange('rightAnswer', e.target.value)}
                  required
                >
                  <option value="">Select correct answer</option>
                  {[form.option1, form.option2, form.option3, form.option4].filter(Boolean).map((opt, i) => (
                    <option key={i} value={opt}>{opt}</option>
                  ))}
                </select>
              </div>

              <div className="admin-form__row-pair">
                <div className="admin-form__row">
                  <label className="form-label">Category *</label>
                  <input
                    type="text"
                    className="form-input"
                    value={form.category}
                    onChange={e => handleFormChange('category', e.target.value)}
                    placeholder="e.g. Java, Python..."
                    required
                    list="cats-list"
                  />
                  <datalist id="cats-list">
                    {categories.map(c => <option key={c} value={c} />)}
                  </datalist>
                </div>
                <div className="admin-form__row">
                  <label className="form-label">Difficulty</label>
                  <div className="difficulty-options">
                    {DIFFICULTIES.map(d => (
                      <button
                        key={d}
                        type="button"
                        className={`difficulty-btn ${form.difficultyLevel === d ? 'difficulty-btn--selected' : ''}`}
                        style={{ '--diff-color': diffColor[d] }}
                        onClick={() => handleFormChange('difficultyLevel', d)}
                      >
                        {d}
                      </button>
                    ))}
                  </div>
                </div>
              </div>

              <div className="admin-form__actions">
                <Button type="button" variant="secondary" onClick={() => { setShowForm(false); setForm(EMPTY_FORM); setEditing(false); }}>
                  Cancel
                </Button>
                <Button type="submit" loading={saving}>
                  {saving ? 'Saving...' : editing ? 'Update Question' : 'Add Question'}
                </Button>
              </div>
            </form>
          </div>
        )}

        {/* Filters */}
        <div className="admin-filters">
          <input
            type="text"
            className="form-input admin-search"
            placeholder="🔍  Search questions..."
            value={search}
            onChange={e => setSearch(e.target.value)}
          />
          <div className="filter-cats">
            <button className={`filter-cat ${!filterCat ? 'filter-cat--active' : ''}`} onClick={() => setFilterCat('')}>
              All ({questions.length})
            </button>
            {categories.map(cat => (
              <button
                key={cat}
                className={`filter-cat ${filterCat === cat ? 'filter-cat--active' : ''}`}
                onClick={() => setFilterCat(cat)}
              >
                {cat} ({questions.filter(q => q.category === cat).length})
              </button>
            ))}
          </div>
        </div>

        {/* Questions Table */}
        <div className="questions-table glass-card">
          {loading ? (
            <div className="questions-loading">
              {Array.from({ length: 5 }).map((_, i) => (
                <div key={i} className="skeleton-row">
                  <div className="skeleton" style={{ height: '16px', width: '60%' }} />
                  <div className="skeleton" style={{ height: '16px', width: '15%' }} />
                  <div className="skeleton" style={{ height: '16px', width: '15%' }} />
                </div>
              ))}
            </div>
          ) : filtered.length === 0 ? (
            <div className="questions-empty">
              <span className="questions-empty__icon">📭</span>
              <p>{questions.length === 0 ? 'No questions yet. Add your first one!' : 'No questions match your filter.'}</p>
              {questions.length === 0 && (
                <Button onClick={() => setShowForm(true)}>Add Question</Button>
              )}
            </div>
          ) : (
            <table className="q-table">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Question</th>
                  <th>Category</th>
                  <th>Difficulty</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {filtered.map((q, idx) => (
                  <tr key={q.id} className="q-row animate-fade-in" style={{ animationDelay: `${idx * 0.03}s` }}>
                    <td className="q-cell q-cell--id">{q.id}</td>
                    <td className="q-cell q-cell--question">
                      <span className="q-title">{q.questionTitle}</span>
                      <div className="q-options-preview">
                        {[q.option1, q.option2, q.option3, q.option4].filter(Boolean).map((opt, i) => (
                          <span
                            key={i}
                            className={`q-opt-chip ${opt === q.rightAnswer ? 'q-opt-chip--correct' : ''}`}
                          >
                            {String.fromCharCode(65 + i)}: {opt}
                          </span>
                        ))}
                      </div>
                    </td>
                    <td className="q-cell">
                      <span className="q-category-badge">{q.category}</span>
                    </td>
                    <td className="q-cell">
                      <span
                        className="q-difficulty-badge"
                        style={{ '--diff-color': diffColor[q.difficultyLevel] || 'var(--text-muted)' }}
                      >
                        {q.difficultyLevel || '—'}
                      </span>
                    </td>
                    <td className="q-cell q-cell--actions">
                      <button className="q-action-btn q-action-btn--edit" onClick={() => handleEdit(q)} title="Edit">✎</button>
                      <button
                        className="q-action-btn q-action-btn--delete"
                        onClick={() => setConfirmDelete(q.id)}
                        disabled={deleting === q.id}
                        title="Delete"
                      >
                        {deleting === q.id ? '...' : '🗑'}
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>

        {/* Delete Confirm */}
        {confirmDelete && (
          <div className="modal-overlay" onClick={() => setConfirmDelete(null)}>
            <div className="modal glass-card animate-scale-in" onClick={e => e.stopPropagation()}>
              <h3 className="modal__title">Delete Question?</h3>
              <p className="modal__body">This action cannot be undone. Question ID: <strong>{confirmDelete}</strong></p>
              <div className="modal__actions">
                <Button variant="secondary" onClick={() => setConfirmDelete(null)}>Cancel</Button>
                <Button variant="danger" onClick={() => handleDelete(confirmDelete)} loading={deleting === confirmDelete}>
                  Delete
                </Button>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminPage;
