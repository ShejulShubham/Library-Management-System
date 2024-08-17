function BorrowList({id, bookName, userName, borrowDate, dueDate, status}){
    return (
        <tr>
        <td>{id}</td>
        <td>{bookName}</td>
        <td>{userName}</td>
        <td>{borrowDate}</td>
        <td>{dueDate}</td>
        <td className="status-returned">{status}</td>
      </tr>
    )
}

export default BorrowList