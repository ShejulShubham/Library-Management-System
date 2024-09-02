function BookHomeList({key, title, authors, thumbnail, link}) {


    return (
        <div className="book-item">
        
        <img src={thumbnail} alt={title} />
        <p><b>{title}</b></p>
        <span>by {authors}</span>
        <button className="view">View</button>
      </div>
    )
}


export default BookHomeList