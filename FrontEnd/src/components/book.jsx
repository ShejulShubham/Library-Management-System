

function BookList({id, title, author, category, quantity}) {
    // console.log(category)
    return (
    <tr>
        <td>{id}</td>
        <td>{title}</td>
        <td>{category}</td>
        <td>{author}</td>
        <td>{quantity}</td>
    </tr>
)
}



export default BookList