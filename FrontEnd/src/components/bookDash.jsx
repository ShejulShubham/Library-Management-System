function bookDash({bId, bName, author, quantity}){
    return (
        <tr>
            <th>{bId}</th>
            <th>{bName}</th>
            <th>{author}</th>
            <th>{quantity}</th>
         </tr>
    )
}

export default bookDash;