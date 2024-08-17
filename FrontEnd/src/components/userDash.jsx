
function userDash({id, name, email}){
    return(
        <tr>
            <th>{id}</th>
            <th>{name}</th>
            <th>{email}</th>
        </tr>
    )
}


export default userDash;