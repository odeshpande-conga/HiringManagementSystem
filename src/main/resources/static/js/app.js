// ========== API Helper ==========
const API_BASE = '/api';

function getToken() {
    return localStorage.getItem('token');
}

function setToken(token) {
    localStorage.setItem('token', token);
}

function setUser(user) {
    localStorage.setItem('user', JSON.stringify(user));
}

function getUser() {
    const u = localStorage.getItem('user');
    return u ? JSON.parse(u) : null;
}

function logout() {
    localStorage.clear();
    window.location.href = '/pages/login.html';
}

async function apiCall(method, url, body = null) {
    const headers = { 'Content-Type': 'application/json' };
    const token = getToken();
    if (token) headers['Authorization'] = 'Bearer ' + token;

    const options = { method, headers };
    if (body) options.body = JSON.stringify(body);

    const response = await fetch(API_BASE + url, options);
    return response.json();
}

// ========== Auth Functions ==========
async function login(email, password) {
    const data = await apiCall('POST', '/auth/login', { email, password });
    if (data.success) {
        setToken(data.data.token);
        setUser({ email: data.data.email, role: data.data.role, fullName: data.data.fullName });
        redirectToDashboard(data.data.role);
    }
    return data;
}

async function register(formData) {
    const data = await apiCall('POST', '/auth/register', formData);
    if (data.success) {
        setToken(data.data.token);
        setUser({ email: data.data.email, role: data.data.role, fullName: data.data.fullName });
        redirectToDashboard(data.data.role);
    }
    return data;
}

function redirectToDashboard(role) {
    switch (role) {
        case 'ADMIN': window.location.href = '/pages/admin.html'; break;
        case 'RECRUITER': window.location.href = '/pages/recruiter-dashboard.html'; break;
        default: window.location.href = '/pages/candidate-dashboard.html'; break;
    }
}

// ========== Job Functions ==========
async function getAllJobs() { return apiCall('GET', '/jobs'); }
async function getJob(id) { return apiCall('GET', '/jobs/' + id); }
async function createJob(job) { return apiCall('POST', '/jobs', job); }
async function searchJobs(keyword) { return apiCall('GET', '/jobs/search?keyword=' + keyword); }
async function filterJobsByLocation(location) { return apiCall('GET', '/jobs/filter?location=' + location); }

// ========== Application Functions ==========
async function applyForJob(jobId, coverLetter) { return apiCall('POST', '/applications', { jobId, coverLetter }); }
async function getMyApplications() { return apiCall('GET', '/applications/my'); }

