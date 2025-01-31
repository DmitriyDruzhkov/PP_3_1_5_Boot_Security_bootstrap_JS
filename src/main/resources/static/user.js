async function getUser() {
    fetch("/api/user")
        .then(res => res.json())
        .then(user => {
            // Убираем "ROLE_" из всех ролей
            const roles = user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')

            $('#navbar-email').append(`<span>${user.email}</span>`)
            $('#navbar-roles').append(`<span>${roles + ' '}</span>`)

            const u = `
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.lastname}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${roles + ' '}</td>
                    </tr>`;
            $('#usertable').append(u)
        })
}
getUser()
