const API = "";
const $ = (id) => document.getElementById(id);

const pages = ["dashboard", "resumeBuilder", "uploadCv", "recommend", "career", "news", "resources", "about", "settings"];

const pageTitleKeys = {
  dashboard: "nav_dashboard",
  resumeBuilder: "nav_resume",
  uploadCv: "nav_upload",
  recommend: "nav_match",
  career: "nav_career",
  news: "nav_news",
  resources: "nav_resources",
  about: "nav_about",
  settings: "nav_settings"
};

const dict = {
  kk: {
    announce: "AI internship платформа дайын. CV жаса -> Match ал -> Стажировкаға өт.",
    eyebrow: "AI INTERNSHIP PLATFORM",
    hero_tag: "AI internship command center",
    hero: "Тағылымдама табуға арналған AI платформа",
    hero_desc: "Резюме құрыңыз, дағдыларды AI арқылы талдаңыз, және тағылымдамаға жылдамырақ жетіңіз.",
    nav_dashboard: "Басты бет",
    nav_resume: "Резюме жасаушы",
    nav_upload: "CV жүктеу",
    nav_match: "Стажировка сәйкестігі",
    nav_career: "Жол картасы",
    nav_news: "Жаңалықтар",
    nav_resources: "Қосымша бөлім",
    nav_about: "Біз туралы",
    nav_settings: "Баптаулар",
    logout: "Шығу",
    cta_upload_resume: "Резюме жүктеу",
    cta_run_match: "AI Match іске қосу",
    cta_build_roadmap: "Roadmap құру",
    cta_bot: "Telegram бот @skillbridgeebot",
    nav_sign_in: "Кіру",
    nav_request_demo: "Demo сұрау",
    hero_chip_1: "Резюме intelligence",
    hero_chip_2: "AI skill analysis",
    hero_chip_3: "Internship acceleration",
    hero_cta_primary: "Мансапты құруды бастау",
    stat_1_label: "Тағылымдамаға дайындықтың артуы",
    stat_2_label: "Жылдамырақ skill analysis",
    stat_3_label: "Орташа дайындық уақыты",
    kpi_user_id: "Қолданушы ID",
    kpi_cv_id: "CV ID",
    kpi_best_match: "Ең жоғары сәйкестік",
    pipe_1: "Резюме құру",
    pipe_2: "Навык шығару",
    pipe_3: "Сәйкестік есептеу",
    pipe_4: "Стажировка алу",
    f1_title: "Резюме жүктеу панелі",
    f1_desc: "CV жүктеп, техникалық навыктарды AI арқылы шығарыңыз.",
    f1_btn: "Жүктеу бөлімі",
    f2_title: "AI навык талдауы",
    f2_desc: "Дайындық деңгейі мен навык картасын көріңіз.",
    f2_btn: "Талдауды ашу",
    f3_title: "Стажировка сәйкестігі",
    f3_desc: "Рөлге сәйкестік пайызы және gap-талдау.",
    f3_btn: "Сәйкестікті көру",
    f4_desc: "3 айлық personalized жоспар.",
    f4_btn: "Жоспар құру",
    dream_title: "Біздің Арман",
    dream_desc: "Жетекші компаниялармен internship тректері.",
    resume_title: "Резюме PDF генераторы",
    resume_desc: "HR 10-15 секундта байқайтындай әсерлі, ATS-friendly резюме.",
    resume_btn_generate: "PDF жасау",
    resume_btn_demo: "Демо толтыру",
    upload_title: "Резюме жүктеу",
    upload_desc: "PDF парсер + AI навык анықтау",
    upload_pdf_file: "PDF файл",
    upload_btn: "Жүктеу және талдау",
    analysis_output: "Талдау нәтижесі",
    readiness: "Дайындық",
    match_title: "Стажировка сәйкестік пайызы",
    match_desc: "AI қолжетімді стажировкалармен рөлге сәйкестік ықтималдығын есептейді.",
    match_btn: "Ұсыныстарды есептеу",
    contact_title: "Толығырақ бағыт-бағдар керек пе?",
    contact_desc: "Telegram арқылы бізге тікелей жазыңыз.",
    career_title: "Career Roadmap",
    career_desc: "10 бағыттың бірін таңдап, келесі 3 ай жоспарын алыңыз.",
    focus_track: "Негізгі бағыт",
    track_backend: "Backend",
    track_frontend: "Frontend",
    track_data_ai: "Data / AI",
    track_devops: "DevOps",
    track_mobile: "Mobile",
    track_qa: "QA Automation",
    track_product: "Product Management",
    track_uiux: "UI/UX Design",
    track_cyber: "Cybersecurity",
    track_cloud: "Cloud Engineering",
    career_btn: "Roadmap жасау",
    roadmap_output: "Roadmap нәтижесі",
    news_title: "Жаңалықтар",
    news_desc: "Стажировка нарығына қатысты соңғы жаңартулар.",
    resources_title: "Ресурстар",
    resources_desc: "Интервью дайындығы мен навык өсіміне арналған материалдар.",
    about_title: "SkillBridge AI туралы",
    about_desc: "Студентті CV-ден internship offer-ге дейін апаратын кәсіби AI платформа.",
    author_1_name: "Yernur Kanash",
    author_1_role: "Founder, Product & AI Integration",
    author_2_name: "Kenesbek Didar",
    author_2_role: "Founder, Product & AI Integration",
    author_3_name: "SkillBridge Core Team",
    author_3_role: "Students, future engineers & startup founders",

    about_strip_title: "Авторлар / Біз туралы",
    about_strip_desc: "SkillBridge AI студенттерге өлшенетін AI match арқылы internship алуға көмектеседі.",
    bot_link: "Telegram ботты ашу @skillbridgeebot",
    settings_title: "Баптаулар",
    settings_desc: "Тақырып пен тіл жергілікті түрде сақталады.",
    auth_kicker: "SkillBridge кіру",
    auth_welcome: "Қайта оралуыңызбен",
    auth_login_tab: "Кіру",
    auth_register_tab: "Тіркелу",
    auth_login_btn: "Кіру",
    auth_register_btn: "Тіркелу",
    form_name: "Аты-жөні",
    form_email: "Email",
    form_password: "Құпиясөз",
    form_university: "Университет",
    form_skills: "Навыктар",
    form_projects: "Жобалар",
    form_languages: "Тілдер",
    no_skills: "Навык табылмады",
    no_recommendations: "Ұсыныс табылмады",
    required: "Қажетті навыктар",
    gap: "Gap",
    no_gap: "Gap жоқ",
    run_exact_match: "Нақты match есептеу",
    source: "Дереккөз",
    open: "Ашу",
    status_ready: "Дайын",
    status_loading: "Жүктелуде",
    status_error: "Қате",
    msg_register_done: "Тіркелу сәтті",
    msg_login_done: "Кіру сәтті",
    msg_need_user_pdf: "User ID және PDF қажет",
    msg_cv_uploaded: "CV жүктелді",
    msg_need_user_id: "User ID қажет",
    msg_recommendations_ready: "Ұсыныстар дайын",
    msg_upload_first: "Алдымен CV жүктеңіз",
    msg_roadmap_ready: "Roadmap дайын",
    msg_pdf_error: "PDF жасау қатесі",
    msg_pdf_downloaded: "PDF жүктелді",
    msg_auth_required: "Бұл мүмкіндікті пайдалану үшін тіркеліңіз немесе кіріңіз.",
    msg_guest_user: "Қонақ",
    track_label: "Бағыт",
    month_1: "1-ай",
    month_2: "2-ай",
    month_3: "3-ай"
  },
  ru: {
    announce: "AI internship платформа готова. Создай CV -> Получи Match -> Устройся.",
    eyebrow: "AI INTERNSHIP PLATFORM",
    hero_tag: "AI internship command center",
    hero: "AI-платформа для успеха на стажировке",
    hero_desc: "Создавайте резюме, анализируйте навыки с AI и быстрее выходите на стажировки.",
    nav_dashboard: "Дашборд",
    nav_resume: "Конструктор резюме",
    nav_upload: "Загрузка CV",
    nav_match: "Стажировки",
    nav_career: "Roadmap",
    nav_news: "Новости",
    nav_resources: "Доп. раздел",
    nav_about: "О нас",
    nav_settings: "Настройки",
    logout: "Выход",
    cta_upload_resume: "Загрузить резюме",
    cta_run_match: "Запустить AI Match",
    cta_build_roadmap: "Построить Roadmap",
    cta_bot: "Telegram-бот @skillbridgeebot",
    nav_sign_in: "Sign In",
    nav_request_demo: "Request Demo",
    hero_chip_1: "Resume intelligence",
    hero_chip_2: "AI skill analysis",
    hero_chip_3: "Internship acceleration",
    hero_cta_primary: "Start Building Your Career",
    stat_1_label: "Рост готовности к стажировке",
    stat_2_label: "Более быстрый skill analysis",
    stat_3_label: "Среднее время подготовки",
    kpi_user_id: "ID пользователя",
    kpi_cv_id: "ID CV",
    kpi_best_match: "Лучший Match",
    pipe_1: "Создай резюме",
    pipe_2: "Извлеки навыки",
    pipe_3: "Посчитай match",
    pipe_4: "Получи стажировку",
    f1_title: "Панель загрузки резюме",
    f1_desc: "Загрузи CV и извлеки технические навыки через AI.",
    f1_btn: "К загрузке",
    f2_title: "AI-анализ навыков",
    f2_desc: "Смотри readiness и карту навыков.",
    f2_btn: "Открыть анализ",
    f3_title: "Стажировка Match",
    f3_desc: "Процент соответствия роли и gap-анализ.",
    f3_btn: "Смотреть match",
    f4_desc: "3-месячный персональный план.",
    f4_btn: "Построить план",
    dream_title: "Наша мечта",
    dream_desc: "Internship-треки с ведущими компаниями.",
    resume_title: "PDF-конструктор резюме",
    resume_desc: "Эффектное ATS-friendly резюме, которое HR заметит за 10-15 секунд.",
    resume_btn_generate: "Сгенерировать PDF",
    resume_btn_demo: "Заполнить демо",
    upload_title: "Загрузка резюме",
    upload_desc: "PDF parser + AI определение навыков",
    upload_pdf_file: "PDF файл",
    upload_btn: "Загрузить и анализировать",
    analysis_output: "Результат анализа",
    readiness: "Готовность",
    match_title: "Процент совпадения стажировок",
    match_desc: "AI считает вероятность соответствия роли по доступным стажировкам.",
    match_btn: "Запустить рекомендации",
    contact_title: "Нужна более детальная консультация?",
    contact_desc: "Напишите нам напрямую в Telegram.",
    career_title: "Career Roadmap",
    career_desc: "Выберите 1 из 10 направлений и получите план на 3 месяца.",
    focus_track: "Основной трек",
    track_backend: "Backend",
    track_frontend: "Frontend",
    track_data_ai: "Data / AI",
    track_devops: "DevOps",
    track_mobile: "Mobile",
    track_qa: "QA Automation",
    track_product: "Product Management",
    track_uiux: "UI/UX Design",
    track_cyber: "Cybersecurity",
    track_cloud: "Cloud Engineering",
    career_btn: "Сгенерировать roadmap",
    roadmap_output: "Результат roadmap",
    news_title: "Новости",
    news_desc: "Последние обновления рынка стажировок.",
    resources_title: "Ресурсы",
    resources_desc: "Материалы для интервью и роста навыков.",
    about_title: "О SkillBridge AI",
    about_desc: "Профессиональная AI-платформа, которая ведет студента от CV к internship offer.",
    author_1_name: "Yernur Kanash",
    author_1_role: "Founder, Product & AI Integration",
    author_2_name: "SkillBridge Core Team",
    author_2_role: "Engineering, Career Science, University Partnerships",
    about_strip_title: "Авторы / О нас",
    about_strip_desc: "SkillBridge AI помогает студентам получить internship через измеримый AI match.",
    bot_link: "Открыть Telegram-бот @skillbridgeebot",
    settings_title: "Настройки",
    settings_desc: "Тема и язык сохраняются локально.",
    auth_kicker: "Доступ SkillBridge",
    auth_welcome: "С возвращением",
    auth_login_tab: "Войти",
    auth_register_tab: "Регистрация",
    auth_login_btn: "Войти",
    auth_register_btn: "Зарегистрироваться",
    form_name: "Имя",
    form_email: "Email",
    form_password: "Пароль",
    form_university: "Университет",
    form_skills: "Навыки",
    form_projects: "Проекты",
    form_languages: "Языки",
    no_skills: "Навыки не найдены",
    no_recommendations: "Рекомендации не найдены",
    required: "Требуемые навыки",
    gap: "Разрыв",
    no_gap: "Без разрыва",
    run_exact_match: "Точный match",
    source: "Источник",
    open: "Открыть",
    status_ready: "Готово",
    status_loading: "Загрузка",
    status_error: "Ошибка",
    msg_register_done: "Регистрация успешна",
    msg_login_done: "Вход выполнен",
    msg_need_user_pdf: "Нужны User ID и PDF",
    msg_cv_uploaded: "CV загружен",
    msg_need_user_id: "Нужен User ID",
    msg_recommendations_ready: "Рекомендации готовы",
    msg_upload_first: "Сначала загрузите CV",
    msg_roadmap_ready: "Roadmap готов",
    msg_pdf_error: "Ошибка PDF",
    msg_pdf_downloaded: "PDF скачан",
    msg_auth_required: "Пожалуйста, зарегистрируйтесь или войдите, чтобы использовать эту функцию.",
    msg_guest_user: "Гость",
    track_label: "Трек",
    month_1: "Месяц 1",
    month_2: "Месяц 2",
    month_3: "Месяц 3"
  },
  en: {
    announce: "Your AI internship platform is live. Build CV -> Match -> Get hired.",
    eyebrow: "AI INTERNSHIP PLATFORM",
    hero_tag: "AI internship command center",
    hero: "The AI-Powered Platform for Internship Success",
    hero_desc: "Build your resume, analyze skills with AI, and land internships faster.",
    nav_dashboard: "Dashboard",
    nav_resume: "Resume Builder",
    nav_upload: "Upload CV",
    nav_match: "Internship Match",
    nav_career: "Roadmap",
    nav_news: "News",
    nav_resources: "Extra section",
    nav_about: "About us",
    nav_settings: "Settings",
    logout: "Logout",
    cta_upload_resume: "Upload Resume",
    cta_run_match: "Run AI Match",
    cta_build_roadmap: "Build Roadmap",
    cta_bot: "Telegram Bot @skillbridgeebot",
    nav_sign_in: "Sign In",
    nav_request_demo: "Request Demo",
    hero_chip_1: "Resume intelligence",
    hero_chip_2: "AI skill analysis",
    hero_chip_3: "Internship acceleration",
    hero_cta_primary: "Start Building Your Career",
    stat_1_label: "Increase in internship readiness",
    stat_2_label: "Faster skill analysis",
    stat_3_label: "Average internship preparation time",
    kpi_user_id: "User ID",
    kpi_cv_id: "CV ID",
    kpi_best_match: "Best Match",
    pipe_1: "Create Resume",
    pipe_2: "Extract Skills",
    pipe_3: "Generate Match",
    pipe_4: "Get Internship",
    f1_title: "Resume Upload Panel",
    f1_desc: "Upload CV and parse technical skills with AI.",
    f1_btn: "Go to Upload",
    f2_title: "AI Skill Analysis",
    f2_desc: "See readiness signal and extracted skill cloud.",
    f2_btn: "Open Analysis",
    f3_title: "Internship Match",
    f3_desc: "Role-fit percentage and gap insights.",
    f3_btn: "View Match",
    f4_desc: "3-month personalized plan.",
    f4_btn: "Build Plan",
    dream_title: "Our Dream",
    dream_desc: "Internship tracks with leading partner companies.",
    resume_title: "Resume PDF Builder",
    resume_desc: "A stronger ATS-friendly resume designed to catch HR attention in 10-15 seconds.",
    resume_btn_generate: "Generate PDF",
    resume_btn_demo: "Fill demo",
    upload_title: "Resume Upload",
    upload_desc: "PDF parser + AI skills detection",
    upload_pdf_file: "PDF File",
    upload_btn: "Upload & Analyze",
    analysis_output: "Analysis Output",
    readiness: "Readiness",
    match_title: "Internship Match Percentage",
    match_desc: "AI calculates role-fit probability against available internships.",
    match_btn: "Run recommendations",
    contact_title: "Need detailed guidance from our team?",
    contact_desc: "Tap Telegram and write directly to us.",
    career_title: "Career Roadmap",
    career_desc: "Choose 1 of 10 tracks and generate a 3-month plan.",
    focus_track: "Focus Track",
    track_backend: "Backend",
    track_frontend: "Frontend",
    track_data_ai: "Data / AI",
    track_devops: "DevOps",
    track_mobile: "Mobile",
    track_qa: "QA Automation",
    track_product: "Product Management",
    track_uiux: "UI/UX Design",
    track_cyber: "Cybersecurity",
    track_cloud: "Cloud Engineering",
    career_btn: "Generate roadmap",
    roadmap_output: "Roadmap Output",
    news_title: "News",
    news_desc: "Latest industry updates for internship landscape.",
    resources_title: "Resources",
    resources_desc: "Curated content for interview prep and skill growth.",
    about_title: "About SkillBridge AI",
    about_desc: "Professional AI platform helping students move from CV to internship offer.",
    author_1_name: "Yernur Kanash",
    author_1_role: "Founder, Product & AI Integration",
    author_2_name: "SkillBridge Core Team",
    author_2_role: "Engineering, Career Science, University Partnerships",
    about_strip_title: "Authors / About us",
    about_strip_desc: "SkillBridge AI helps students move from CV to internship offer with measurable AI matching.",
    bot_link: "Open Telegram Bot @skillbridgeebot",
    settings_title: "Settings",
    settings_desc: "Theme and language are saved locally.",
    auth_kicker: "SkillBridge Access",
    auth_welcome: "Welcome back",
    auth_login_tab: "Login",
    auth_register_tab: "Register",
    auth_login_btn: "Login",
    auth_register_btn: "Register",
    form_name: "Name",
    form_email: "Email",
    form_password: "Password",
    form_university: "University",
    form_skills: "Skills",
    form_projects: "Projects",
    form_languages: "Languages",
    no_skills: "No skills",
    no_recommendations: "No recommendations",
    required: "Required",
    gap: "Gap",
    no_gap: "No gap",
    run_exact_match: "Run exact match",
    source: "Source",
    open: "Open",
    status_ready: "Ready",
    status_loading: "Loading",
    status_error: "Error",
    msg_register_done: "Register done",
    msg_login_done: "Login done",
    msg_need_user_pdf: "User ID and PDF are required",
    msg_cv_uploaded: "CV uploaded",
    msg_need_user_id: "User ID is required",
    msg_recommendations_ready: "Recommendations ready",
    msg_upload_first: "Upload CV first",
    msg_roadmap_ready: "Roadmap ready",
    msg_pdf_error: "PDF error",
    msg_pdf_downloaded: "PDF downloaded",
    msg_auth_required: "Please sign up or log in to use this feature.",
    msg_guest_user: "Guest",
    track_label: "Track",
    month_1: "Month 1",
    month_2: "Month 2",
    month_3: "Month 3"
  }
};

const store = {
  user: null,
  cv: null,
  bestMatch: 0,
  theme: localStorage.getItem("sb_theme") || "dark",
  lang: localStorage.getItem("sb_lang") || "kk",
  pendingAction: null,
  pendingPage: null
};

function tr(key, fallback = "") {
  return dict[store.lang]?.[key] || dict.kk[key] || fallback || key;
}

function toast(msg) {
  const t = $("toast");
  t.textContent = msg;
  t.classList.add("show");
  setTimeout(() => t.classList.remove("show"), 1500);
}

function setStatus(token) {
  $("statusPill").textContent = `● ${tr(`status_${token}`, token)}`;
}

function applyTheme(theme) {
  document.documentElement.dataset.theme = theme;
  $("themeToggle").textContent = theme === "dark" ? "☀️" : "🌙";
}

function applyLang(lang) {
  const d = dict[lang] || dict.kk;
  document.documentElement.lang = lang;

  document.querySelectorAll("[data-i18n]").forEach((el) => {
    const key = el.getAttribute("data-i18n");
    if (d[key]) {
      el.textContent = d[key];
    }
  });

  const current = document.querySelector(".nav-item.active")?.dataset.page || "dashboard";
  showPage(current);
}

function savePrefs() {
  localStorage.setItem("sb_theme", store.theme);
  localStorage.setItem("sb_lang", store.lang);
}

function showPage(name) {
  document.body.dataset.page = name;

  pages.forEach((p) => {
    const el = $(`page-${p}`);
    if (el) {
      el.classList.toggle("show", p === name);
    }
  });

  document.querySelectorAll(".nav-item").forEach((b) => {
    b.classList.toggle("active", b.dataset.page === name);
  });

  const key = pageTitleKeys[name];
  $("pageTitle").textContent = key ? tr(key, "SkillBridge") : "SkillBridge";
}

async function api(method, url, body, isForm = false) {
  setStatus("loading");
  const opts = { method, headers: {} };

  if (body) {
    if (isForm) {
      opts.body = body;
    } else {
      opts.headers["Content-Type"] = "application/json";
      opts.body = JSON.stringify(body);
    }
  }

  const res = await fetch(API + url, opts);
  const text = await res.text();
  let data;

  try {
    data = JSON.parse(text);
  } catch {
    data = text;
  }

  if (!res.ok) {
    const msg = typeof data === "string" ? data : (data?.errors?.[0] || "API error");
    setStatus("error");
    throw new Error(msg);
  }

  setStatus("ready");
  return data;
}

function renderProfile() {
  if (!store.user) {
    $("btnOpenLogin").hidden = false;
    $("btnOpenRegister").hidden = false;
    $("btnLogout").hidden = true;
    $("userName").textContent = tr("msg_guest_user", "Guest");
    $("userMail").textContent = "-";
    $("avatar").textContent = "G";
    $("dashUserId").textContent = "-";
    $("upUserId").value = "";
    $("rUserId").value = "";
    return;
  }

  $("btnOpenLogin").hidden = true;
  $("btnOpenRegister").hidden = true;
  $("btnLogout").hidden = false;
  $("userName").textContent = store.user.name;
  $("userMail").textContent = store.user.email;
  $("avatar").textContent = (store.user.name || "U")[0].toUpperCase();
  $("dashUserId").textContent = store.user.id;
  $("upUserId").value = store.user.id;
  $("rUserId").value = store.user.id;
}

function openAuthModal(message = "") {
  $("authModal").style.display = "grid";
  if (message) {
    toast(message);
  }
}

function closeAuthModal() {
  $("authModal").style.display = "none";
}

function openLoginModal() {
  $("tabLogin").classList.add("active");
  $("tabRegister").classList.remove("active");
  $("loginForm").hidden = false;
  $("registerForm").hidden = true;
  openAuthModal();
}

function openRegisterModal() {
  $("tabRegister").classList.add("active");
  $("tabLogin").classList.remove("active");
  $("loginForm").hidden = true;
  $("registerForm").hidden = false;
  openAuthModal();
}

async function runPendingAction() {
  if (!store.pendingAction) {
    return;
  }

  const action = store.pendingAction;
  const page = store.pendingPage;
  store.pendingAction = null;
  store.pendingPage = null;

  if (page) {
    showPage(page);
  }

  await action();
}

async function guardAction(action, page) {
  if (store.user) {
    if (page) {
      showPage(page);
    }
    await action();
    return;
  }

  store.pendingAction = action;
  store.pendingPage = page || document.querySelector(".nav-item.active")?.dataset.page || "dashboard";
  openAuthModal(tr("msg_auth_required", "Please sign up or log in to use this feature."));
}

function renderSkills(target, skillsList) {
  target.innerHTML = "";
  if (!skillsList?.length) {
    target.innerHTML = `<span class='muted tiny'>${tr("no_skills", "No skills")}</span>`;
    return;
  }

  skillsList.forEach((s) => {
    const tag = document.createElement("span");
    tag.className = "tag";
    tag.textContent = s;
    target.appendChild(tag);
  });
}

function localizeResource(item) {
  const key = item.title?.trim();
  const map = {
    "LeetCode 75": {
      kk: { type: "Тәжірибе", description: "Интервьюге дайындыққа арналған базалық алгоритмдік сет." },
      ru: { type: "Практика", description: "Базовый алгоритмический набор для подготовки к интервью." },
      en: { type: "Practice", description: "Core algorithm set for interview preparation." }
    },
    "Roadmap.sh": {
      kk: { type: "Жол картасы", description: "Backend, frontend және data бағыттары бойынша интерактив жол картасы." },
      ru: { type: "Дорожная карта", description: "Интерактивная дорожная карта по backend, frontend и data направлениям." },
      en: { type: "Roadmap", description: "Interactive roadmap for backend, frontend, and data tracks." }
    },
    "CV Checklist": {
      kk: { type: "Карьера", description: "ATS-friendly резюме үшін қысқа чеклист." },
      ru: { type: "Карьера", description: "Короткий чеклист для ATS-friendly резюме." },
      en: { type: "Career", description: "A short checklist for ATS-friendly resumes." }
    }
  };

  const i18n = map[key]?.[store.lang];
  if (!i18n) return item;
  return { ...item, ...i18n };
}

async function register() {
  const payload = {
    name: $("regName").value.trim(),
    email: $("regEmail").value.trim(),
    password: $("regPassword").value
  };

  const data = await api("POST", "/api/auth/register", payload);
  store.user = data;
  localStorage.setItem("sb_user_id", String(data.id));
  closeAuthModal();
  renderProfile();
  toast(tr("msg_register_done", "Register done"));
  await runPendingAction();
}

async function login() {
  const payload = {
    email: $("loginEmail").value.trim(),
    password: $("loginPassword").value
  };

  const data = await api("POST", "/api/auth/login", payload);
  store.user = data;
  localStorage.setItem("sb_user_id", String(data.id));
  closeAuthModal();
  renderProfile();
  toast(tr("msg_login_done", "Login done"));
  await runPendingAction();
}

async function loadProfileFromStorage() {
  const userId = localStorage.getItem("sb_user_id");
  if (!userId) return;

  try {
    const data = await api("GET", `/api/auth/profile?userId=${userId}`);
    store.user = data;
    closeAuthModal();
    renderProfile();
  } catch {
    localStorage.removeItem("sb_user_id");
    store.user = null;
    renderProfile();
  }
}

async function uploadCv() {
  const userId = $("upUserId").value;
  const file = $("upFile").files[0];

  if (!userId || !file) {
    toast(tr("msg_need_user_pdf", "User ID and PDF are required"));
    return;
  }

  const fd = new FormData();
  fd.append("userId", userId);
  fd.append("file", file);

  const data = await api("POST", "/api/cv/upload", fd, true);
  store.cv = data;
  $("upCvId").textContent = data.id;
  $("dashCvId").textContent = data.id;
  $("upScore").textContent = `${data.readinessScore}%`;
  renderSkills($("upSkillTags"), data.skillsList || []);
  toast(tr("msg_cv_uploaded", "CV uploaded"));
}

async function runRecommendations() {
  const userId = $("rUserId").value;
  if (!userId) {
    toast(tr("msg_need_user_id", "User ID is required"));
    return;
  }

  const data = await api("GET", `/api/match/recommendations?userId=${userId}`);
  const list = $("recList");
  list.innerHTML = "";

  if (!data.length) {
    list.innerHTML = `<div class='muted'>${tr("no_recommendations", "No recommendations")}</div>`;
    return;
  }

  store.bestMatch = Number(data[0].matchPercent || 0).toFixed(1);
  $("dashBest").textContent = `${store.bestMatch}%`;

  data.forEach((item) => {
    const card = document.createElement("div");
    card.className = "rec-card";
    card.innerHTML = `
      <div class="rec-top">
        <div>
          <h3 class="rec-title">${item.internship.title}</h3>
          <p class="rec-sub muted">${item.internship.companyName}</p>
        </div>
        <div class="rec-score">${Number(item.matchPercent).toFixed(1)}%</div>
      </div>
      <div class="muted tiny">${tr("required", "Required")}: ${item.internship.requiredSkills || "-"}</div>
      <div class="muted tiny">${tr("gap", "Gap")}: ${item.skillGap || tr("no_gap", "No gap")}</div>
      <div class="row mt-sm">
        <button class="btn ghost btn-sm" data-cvid="${store.cv?.id || ""}" data-internshipid="${item.internship.id}" data-action="run-match">${tr("run_exact_match", "Run exact match")}</button>
      </div>
    `;
    list.appendChild(card);
  });

  toast(tr("msg_recommendations_ready", "Recommendations ready"));
}

async function runMatch(cvId, internshipId) {
  if (!cvId || !internshipId) {
    toast(tr("msg_upload_first", "Upload CV first"));
    return;
  }

  const data = await api("POST", `/api/match/run?cvId=${cvId}&internshipId=${internshipId}`);
  toast(`Match: ${Number(data.matchPercent).toFixed(1)}%`);
}

async function generateRoadmap() {
  const interest = $("interest").value;
  const data = await api("POST", "/api/career/roadmap", { interest });

  const box = $("roadmapBox");
  box.innerHTML = `
    <div><strong>${tr("track_label", "Track")}:</strong> ${data.track}</div>
    <div class="divider"></div>
    <div><strong>${tr("month_1", "Month 1")}</strong><ul>${data.month1.map((x) => `<li>${x}</li>`).join("")}</ul></div>
    <div><strong>${tr("month_2", "Month 2")}</strong><ul>${data.month2.map((x) => `<li>${x}</li>`).join("")}</ul></div>
    <div><strong>${tr("month_3", "Month 3")}</strong><ul>${data.month3.map((x) => `<li>${x}</li>`).join("")}</ul></div>
  `;

  toast(tr("msg_roadmap_ready", "Roadmap ready"));
}

async function loadNews() {
  const data = await api("GET", "/api/content/news");
  const box = $("newsList");
  box.innerHTML = "";

  data.forEach((n) => {
    const card = document.createElement("div");
    card.className = "news-card";
    card.innerHTML = `
      <div class="tag">${n.category}</div>
      <h3>${n.title}</h3>
      <p class="muted">${n.summary}</p>
      <a href="${n.url}" target="_blank">${tr("source", "Source")}</a>
    `;
    box.appendChild(card);
  });
}

async function loadResources() {
  const data = await api("GET", "/api/content/resources");
  const box = $("resourceList");
  box.innerHTML = "";

  data.map(localizeResource).forEach((r) => {
    const card = document.createElement("div");
    card.className = "news-card";
    card.innerHTML = `
      <div class="tag">${r.type}</div>
      <h3>${r.title}</h3>
      <p class="muted">${r.description}</p>
      <a href="${r.link}" target="_blank">${tr("open", "Open")}</a>
    `;
    box.appendChild(card);
  });
}

async function generatePdf() {
  const payload = {
    name: $("rbName").value,
    email: $("rbEmail").value,
    university: $("rbUniversity").value,
    skills: $("rbSkills").value,
    projects: $("rbProjects").value,
    languages: $("rbLang").value
  };

  const res = await fetch("/api/resume/generate", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  });

  if (!res.ok) {
    toast(tr("msg_pdf_error", "PDF error"));
    return;
  }

  const blob = await res.blob();
  const link = document.createElement("a");
  link.href = URL.createObjectURL(blob);
  link.download = "resume.pdf";
  link.click();
  toast(tr("msg_pdf_downloaded", "PDF downloaded"));
}

function fillDemoResume() {
  $("rbName").value = store.user?.name || "Demo User";
  $("rbEmail").value = store.user?.email || "demo@skillbridge.kz";
  $("rbUniversity").value = "Satbayev University";
  $("rbSkills").value = "Java, Spring Boot, React, SQL, Docker, CI/CD, REST, Git";
  $("rbProjects").value = "AI Internship Match Platform | Built full-stack matching engine; improved recommendation relevance by 34%; launched roadmap assistant used by 500+ students";
  $("rbLang").value = "Kazakh, Russian, English";
}

function logout() {
  store.user = null;
  store.cv = null;
  localStorage.removeItem("sb_user_id");
  store.pendingAction = null;
  store.pendingPage = null;
  closeAuthModal();
  renderProfile();
}

function initEvents() {
  document.addEventListener("click", async (e) => {
    const protectedPages = new Set(["resumeBuilder", "uploadCv", "recommend", "career"]);

    const nav = e.target.closest(".nav-item");
    if (nav) {
      showPage(nav.dataset.page);
      if (nav.dataset.page === "news") await loadNews();
      if (nav.dataset.page === "resources") await loadResources();
    }

    const go = e.target.closest("[data-go]");
    if (go) {
      if (protectedPages.has(go.dataset.go)) {
        await guardAction(async () => {}, go.dataset.go);
      } else {
        showPage(go.dataset.go);
        if (go.dataset.go === "news") await loadNews();
        if (go.dataset.go === "resources") await loadResources();
      }
    }

    const runBtn = e.target.closest("[data-action='run-match']");
    if (runBtn) {
      await guardAction(
        async () => runMatch(runBtn.dataset.cvid, runBtn.dataset.internshipid),
        "recommend"
      );
    }
  });

  $("tabLogin").addEventListener("click", () => {
    $("tabLogin").classList.add("active");
    $("tabRegister").classList.remove("active");
    $("loginForm").hidden = false;
    $("registerForm").hidden = true;
  });

  $("tabRegister").addEventListener("click", () => {
    $("tabRegister").classList.add("active");
    $("tabLogin").classList.remove("active");
    $("loginForm").hidden = true;
    $("registerForm").hidden = false;
  });

  $("btnRegister").addEventListener("click", async () => {
    try {
      await register();
    } catch (e) {
      toast(e.message);
    }
  });

  $("btnLogin").addEventListener("click", async () => {
    try {
      await login();
    } catch (e) {
      toast(e.message);
    }
  });

  $("btnUploadCv").addEventListener("click", async () => {
    try {
      await guardAction(uploadCv, "uploadCv");
    } catch (e) {
      toast(e.message);
    }
  });

  $("btnRecommend").addEventListener("click", async () => {
    try {
      await guardAction(runRecommendations, "recommend");
    } catch (e) {
      toast(e.message);
    }
  });

  $("btnRoadmap").addEventListener("click", async () => {
    try {
      await guardAction(generateRoadmap, "career");
    } catch (e) {
      toast(e.message);
    }
  });

  $("btnGeneratePdf").addEventListener("click", async () => {
    await guardAction(generatePdf, "resumeBuilder");
  });

  $("btnFillDemo").addEventListener("click", async () => {
    await guardAction(async () => {
      fillDemoResume();
      toast(tr("resume_btn_demo", "Fill demo"));
    }, "resumeBuilder");
  });

  $("btnOpenLogin").addEventListener("click", () => {
    openLoginModal();
  });

  $("btnOpenRegister").addEventListener("click", () => {
    openRegisterModal();
  });

  $("btnLogout").addEventListener("click", logout);

  $("themeToggle").addEventListener("click", () => {
    store.theme = store.theme === "light" ? "dark" : "light";
    applyTheme(store.theme);
    savePrefs();
  });

  $("langSelect").addEventListener("change", async () => {
    store.lang = $("langSelect").value;
    applyLang(store.lang);
    savePrefs();
    await Promise.all([loadNews(), loadResources()]);
  });
}

async function init() {
  applyTheme(store.theme);
  $("langSelect").value = store.lang;
  applyLang(store.lang);
  renderProfile();
  initEvents();
  showPage("dashboard");

  try {
    await loadProfileFromStorage();
    await Promise.all([loadNews(), loadResources()]);
  } catch (e) {
    toast(e.message);
  }

  setStatus("ready");
}

init();



