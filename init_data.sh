# 환경 변수 로드
export $(grep -v '^#' /app/backend/.env | xargs)


mysql -h mysqldb -u root -p${MYSQL_ROOT_PASSWORD} <<EOF
USE ${MYSQL_DATABASE};
INSERT INTO positions (name) VALUES
('backend'),
('frontend'),
('mobile'),
('etc');

INSERT INTO tech_stacks (name, position_id) VALUES
('Spring', 1),
('Nodejs', 1),
('Django', 1),
('Flask', 1),
('Ruby', 1),
('php', 1),
('Go', 1),
('MySQL', 1),
('MongoDB', 1),
('JavaScript', 2),
('TypeScript', 2),
('React', 2),
('Vue', 2),
('Svelte', 2),
('Nextjs', 2),
('Flutter', 3),
('Swift', 3),
('Kotlin', 3),
('ReactNative', 3),
('Unity', 3),
('AWS', 4),
('Docker', 4),
('Kubernetes', 4),
('Figma', 4),
('Git', 4);
EOF
