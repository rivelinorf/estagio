var validString = function (text) {
    if (!isNaN(text) && text != ' ')
        return false;
    else
        return true;
}