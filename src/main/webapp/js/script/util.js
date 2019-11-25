'use strict'

function htmlToPlaintext(text) {
	return text ? String(text).replace(/<[^>]+>/gm, '') : '';
}